<template>
    <div class="manageHotel-wrapper">
        <a-tabs>
            <a-tab-pane tab="用户信用充值" key="1">
                <a-input-search
                        v-if="visible"
                        placeholder="请输入充值的金额（单位人民币元）"
                        enter-button="充值"
                        size="large"
                        @search="goadd"
                />
                 <a-table
                    :columns="column_user"
                    :dataSource="clientList"
                    bordered
                >
                    <span slot="action" slot-scope="record">
                        <a-button type="primary" size="small" @click="addCredit(record)">信用充值</a-button>
                    </span>
                </a-table>
            </a-tab-pane>
            <a-tab-pane tab="异常订单管理" key="2">
                <a-input-search
                        v-if="visible2"
                        placeholder="请输入恢复的信用值百分比（单位%）"
                        enter-button="恢复"
                        size="large"
                        @search="gorecover"
                />
                <a-table
                    :columns="columns_order"
                    :dataSource="orderList"
                    bordered
                >
                    <span slot="price" slot-scope="text">
                        <span>￥{{ text }}</span>
                    </span>
                    <span slot="roomType" slot-scope="text">
                        <span v-if="text == 'BigBed'">大床房</span>
                        <span v-if="text == 'DoubleBed'">双床房</span>
                        <span v-if="text == 'Family'">家庭房</span>
                    </span>
                    <span slot="action" slot-scope="record">
                        <a-popconfirm
                            title="确定想撤销该异常订单吗？"
                            @confirm="recover(record)"
                            okText="确定"
                            cancelText="取消"
                        >
                            <a-button type="danger" size="small" >撤销该异常</a-button>
                        </a-popconfirm>
                    </span>
                </a-table>
            </a-tab-pane>
            <a-tab-pane tab="优惠策略管理" key=3>
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="addCoupons"><a-icon type="plus" />
                        添加优惠策略</a-button>
                </div>
                <a-table
                        :columns="columns_coupon"
                        :dataSource="couponList"
                        bordered
                >
                    <a-tag color="red" slot="couponType" slot-scope="text">
                        <span v-if="text == 1">XX1特惠</span>
                        <span v-if="text == 2">XX2特惠</span>
                        <span v-if="text == 3">满减特惠</span>
                        <span v-if="text == 4">限时特惠</span>
                    </a-tag>
                </a-table>
            </a-tab-pane>
            
        </a-tabs>
        <AddCoupon></AddCoupon>
    </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
import AddCoupon from "./components/addCoupon";
import { message } from 'ant-design-vue';
const column_user = [
    {
        title: '用户名',
        dataIndex: 'userName',
    },
    {
        title: '邮箱',
        dataIndex: 'email',
    },
    {
        title: '手机号',
        dataIndex: 'phoneNumber',
    },
    {
        title: '信用值',
        dataIndex: 'credit'
    },
    {
      title: '操作',
      key: 'action',
      scopedSlots: { customRender: 'action' },
    },
  ];
const columns_order = [
    {
        title: '订单号',
        dataIndex: 'id',
    },
    {
        title: '酒店名',
        dataIndex: 'hotelName',
    },
    {
        title: '房型',
        dataIndex: 'roomType',
        scopedSlots: { customRender: 'roomType' }
    },
    {
        title: '入住时间',
        dataIndex: 'checkInDate',
        scopedSlots: { customRender: 'checkInDate' }
    },
    {
        title: '离店时间',
        dataIndex: 'checkOutDate',
        scopedSlots: { customRender: 'checkOutDate' }
    },
    {
        title: '入住人数',
        dataIndex: 'peopleNum',
    },
    {
        title: '房价',
        dataIndex: 'price',
    },
    {
        title: '状态',
        dataIndex: 'orderState',
        scopedSlots: { customRender: 'orderState' }
    },
    {
        title: '操作',
        key: 'action',
        scopedSlots: { customRender: 'action' },
    },
];
const columns_coupon = [
    {
        title: '优惠类型',
        dataIndex: 'couponType',
        scopedSlots: {customRender: 'couponType'}
    },
    {
        title:"名称",
        dataIndex: 'couponName',
    },
    {
        title: '优惠简介',
        dataIndex: 'description',
    },
    {
        title: '开始时间',
        dataIndex: 'startTime',
    },
    {
        title: '结束时间',
        dataIndex: 'endTime',
    },
    {
        title:"优惠金额",
        dataIndex: 'discountMoney',
    },
];
export default {
    name: 'manageHotel',
    data(){
        return {
            formLayout: 'horizontal',
            pagination: {},
            column_user,
            columns_order,
            columns_coupon,
            visible:false,
            visible2:false,
            activeClientId:-1,
            orderId:-1,
            form: this.$form.createForm(this, { name: 'manageHotel' }),
        }
    },
    components: {
        AddCoupon,
    },
    computed: {
        ...mapGetters([
            'orderList',
            'hotelList',
            'activeHotelId',
            'couponVisible',
            'couponList',
            'clientList',
            'addCouponVisible'
        ]),
    },
    async mounted() {
        await this.set_activeHotelId(-1)
        await this.getHotelCoupon()
        await this.getAllOrders()
        await this.getClientList()
    },
    methods: {
        ...mapMutations([
            'set_activeHotelId',
            'set_addCouponVisible',
        ]),
        ...mapActions([
            'getHotelList',
            'getAllOrders',
            'getHotelCoupon',
            'getClientList',
            'rechargeCredit',
            'cancelAbnormalOrder'
        ]),
        addCoupons(){
            this.set_addCouponVisible(true)
        },
        addCredit(data){
            this.visible=true;
            this.activeClientId=data.id;
        },
        /**
         * @author xyx&&dqj
         * @Description:添加信用
         */
        goadd(value){
            if (value < 0){
                message.warning("请输入有效的金额")
                value = 0
                return
            }
            const data={
                userId:Number(this.activeClientId),
                changeCredit:Number(value)*100
            }
            this.activeClientId=-1;
            this.visible=false;
            this.rechargeCredit(data)
        },
        recover(data){
            this.visible2=true;
            this.orderId=data.id;
        },
        /**
         * @author xyx&&dqj
         * @Description:恢复信用
         */
        gorecover(value){
            if (value < 0 || value > 100){
                message.warning("请输入有效的比例")
                value = 0
                return
            }
            const data={
                orderId:Number(this.orderId),
                ratio:Number(value)
            }
            this.activeClientId=-1;
            this.visible2=false;
            this.cancelAbnormalOrder(data)
        },
    },
}
</script>
<style scoped lang="less">
    .manageHotel-wrapper {
        padding: 50px;
        .chart {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-top: 20px
        }
    }
</style>
<style lang="less">
    .manageHotel-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">
    
</style>
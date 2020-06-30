<template>
    <div class="hotelDesk-wrapper">
        <a-tabs>
            <a-tab-pane tab="酒店信息管理" key="1">
                <a-form :form="form" style="margin-top: 30px">
                    <a-form-item label="酒店名" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1  }">
                        <a-input
                                placeholder="请填写用户名"
                                v-decorator="['hotelName', { rules: [{ required: true, message: '请输入酒店名' }] }]"
                                v-if="modify"
                        />
                        <span v-else>{{ thisHotelInfo.name }}</span>
                    </a-form-item>

                    <a-form-item label="酒店地址" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <a-input
                                placeholder="请填写酒店地址"
                                v-decorator="['hotelLocation', { rules: [{ required: true, message: '请输入酒店地址' }] }]"
                                v-if="modify"
                        />
                        <span v-else>{{ thisHotelInfo.address }}</span>
                    </a-form-item>
                    <a-form-item label="酒店商圈" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <a-input
                                placeholder="请填写酒店商圈"
                                v-decorator="['bizRegion', { rules: [{ required: true, message: '请输入酒店商圈' }] }]"
                                v-if="modify"
                        />
                        <span v-else>{{ thisHotelInfo.bizRegion }}</span>
                    </a-form-item>
                    <a-form-item label="酒店星级" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <a-select
                            v-if="modify"
                            v-decorator="[
                            'hotelStar',
                            { rules: [{ required: true, message: '请选择酒店星级' }] }]"
                        >
                        <a-select-option value="Three">三星级</a-select-option>
                        <a-select-option value="Four">四星级</a-select-option>
                        <a-select-option value="Five">五星级</a-select-option>
                        </a-select>
                        <span v-else>{{ thisHotelInfo.hotelStar}}</span>
                    </a-form-item>
                    <a-form-item label="酒店电话" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <a-input
                                placeholder="请填写酒店电话"
                                v-decorator="['phoneNumber', { rules: [{ required: true, message: '请输入酒店电话' }] }]"
                                v-if="modify"
                        />
                        <span v-else>{{ thisHotelInfo.phoneNum }}</span>
                    </a-form-item>
                    <a-form-item label="酒店描述" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }" >
                        <a-input
                                placeholder="请输入酒店描述"
                                v-decorator="['description', { rules: [{ required: true, message: '请输入酒店描述' }] }]"
                                v-if="modify"
                        />
                        <span v-else>{{ thisHotelInfo.description }}</span>
                    </a-form-item>
                    <a-form-item :wrapper-col="{ span: 12, offset: 5 }" v-if="modify">
                        <a-button type="primary" @click="saveModify">
                            保存
                        </a-button>
                        <a-button type="default" style="margin-left: 30px" @click="cancelModify">
                            取消
                        </a-button>
                    </a-form-item>
                    <a-form-item :wrapper-col="{ span: 8, offset: 4 }" v-else>
                        <a-button type="primary" @click="modifyInfo">
                            修改酒店信息
                        </a-button>
                        <a-divider type="vertical"></a-divider>
                        
                        <a-divider type="vertical"></a-divider>
                        
                    </a-form-item>
                </a-form>
            </a-tab-pane>
            <a-tab-pane tab="酒店订单管理" key="2">
                <a-table
                        :columns="columns_order"
                        :dataSource="hotelOrderList"
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
                    <span slot="action" slot-scope="state">
                        <a-button v-if="state.orderState == '已预订'" type="primary" size="small" @click="checkIn(state.id)">入住</a-button>
                         <a-divider v-if="state.orderState == '已预订'" type="vertical"></a-divider>
                        <a-button  v-if="state.orderState == '已入住'" size="small" @click="checkOut(state.id)">退房</a-button>
                        <!-- <a-divider type="vertical"></a-divider> -->
                        <a-button v-if="state.orderState == '已预订'" type="primary" size="small" @click="overdue(state.id)">逾期</a-button>
<!--                         <a-divider type="vertical" ></a-divider>-->
                        <a-button v-if="state.orderState == '异常'" size="small" @click="recheck(state.id)">延迟入住</a-button>
                        <!-- <a-divider type="vertical"></a-divider> -->
                        <!-- 异常 -->
                    </span>
                </a-table>
            </a-tab-pane>
            <a-tab-pane tab="优惠策略管理" key=3>
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="addCoupon"><a-icon type="plus" />
                        添加优惠策略</a-button>
                </div>
                <a-table
                        :columns="columns_coupon"
                        :dataSource="hotelCouponList"
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
            <a-tab-pane tab="录入客房" key=4>
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="addRooms"><a-icon type="plus-circle" />
                        录入房间</a-button>
                </div>
            </a-tab-pane>

        </a-tabs>
        <AddHotelCoupon></AddHotelCoupon>
        <AddHotelRoom></AddHotelRoom>
    </div>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    import AddHotelCoupon from "./components/addHotelCoupon";
    import AddHotelRoom from "./components/addHotelRoom";
    const columns_order = [
        {
            title: '订单号',
            dataIndex: 'id',
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
            filters: [{ text: '已预订', value: '已预订' },  { text: '已入住', value: '已入住' },{ text: '异常', value: '异常' },{ text: '待评价', value: '待评价' }],
            onFilter: (value, record) => record.orderState.includes(value),
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
        // 这里定义列表头
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
            title:"折扣",
            dataIndex: 'discount',
        },
        {
            title: '优惠简介',
            dataIndex: 'description',
        },
        {
            title:"优惠金额",
            dataIndex: 'discountMoney',
        },
    ];
    export default {
        name: 'frontDesk',
        data(){
            return {
                columns_order,
                columns_coupon,
                modify: false,
                formLayout: 'horizontal',
                pagination: {},
                form: this.$form.createForm(this, { name: 'frontDesk' }),
            }
        },
        components: {
            AddHotelCoupon,
            AddHotelRoom
        },
        computed: {
            ...mapGetters([
                'hotelList',
                'hotelOrderList',
                'userId',
                'currentHotelInfo',
                'thisHotelInfo',
                'chargeHotelId',
                'hotelCouponList',
                'addHotelCouponVisible',
                'addHotelRoomVisible'
            ]),
        },
        async mounted() {
            await this.getHotelOrdersByStaffId()
            await this.getChargeHotelIdByStaffId()
            await this.getChargeHotelById()
            await this.getThisHotelCoupon()
        },
        methods: {
            ...mapMutations([
                'set_hotelOrderList',
                'set_chargeHotelId',
                'set_thisHotelInfo',
                'set_addHotelCouponVisible',
                'set_addHotelRoomVisible'
            ]),
            ...mapActions([
                'getHotelOrdersByStaffId',
                'getChargeHotelIdByStaffId',
                'getChargeHotelById',
                'updateHotelInfo',
                'getThisHotelCoupon',
                'recheckOrder',
                'overdueOrder',
                'checkoutOrder',
                'checkInOrder',
            ]),
            saveModify() {
                this.form.validateFields((err, values) => {
                    if (!err) {
                        const data = {
                            hotelName: this.form.getFieldValue('hotelName'),
                            hotelLocation:this.form.getFieldValue('hotelLocation'),
                            phoneNumber: this.form.getFieldValue('phoneNumber'),
                            description: this.form.getFieldValue('description'),
                            hotelStar:this.form.getFieldValue('hotelStar'),
                            bizRegion: this.form.getFieldValue('bizRegion'),
                        }
                        this.updateHotelInfo(data).then(()=>{
                            this.modify = false
                        })
                    }
                });
            },
            modifyInfo() {
                setTimeout(() => {
                    this.form.setFieldsValue({
                        'hotelName': this.thisHotelInfo.name,
                        'hotelLocation':this.thisHotelInfo.address,
                        'phoneNumber':this.thisHotelInfo.phoneNum,
                        'description':this.thisHotelInfo.description,
                        'hotelStar':this.thisHotelInfo.hotelStar,
                        'bizRegion':this.thisHotelInfo.bizRegion,
                    })
                }, 0)
                this.modify = true
            },
            cancelModify() {
                this.modify = false
            },
            checkIn(id){
                this.checkInOrder(id)
            },
            checkOut(id){
                this.checkoutOrder(id)
            },
            overdue(id){
                this.overdueOrder(id)
            },
            recheck(id){
                this.recheckOrder(id)
            },
            addCoupon(){
                this.set_addHotelCouponVisible(true)
            },
            addRooms(){
                this.set_addHotelRoomVisible(true)
            },

        }
    }
</script>
<style scoped lang="less">
    .hotelDesk-wrapper {
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
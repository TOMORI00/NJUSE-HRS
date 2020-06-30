<template>
    <div class="info-wrapper">
        <a-tabs>
            <a-tab-pane tab="我的信息" key="1">
                <a-form :form="form" style="margin-top: 30px">
                    
                    <a-form-item label="用户名" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1  }">
                        <a-input
                            placeholder="请填写用户名"
                            v-decorator="['userName', { rules: [{ required: true, message: '请输入用户名' }] }]"
                            v-if="modify"
                        />
                        <span v-else>{{ userInfo.userName }}</span>
                    </a-form-item>
                    <a-form-item label="邮箱" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <span>{{ userInfo.email }}</span>
                    </a-form-item>
                    
                    <a-form-item label="手机号" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <a-input
                            placeholder="请填写手机号"
                            v-decorator="[
                                'phoneNumber', 
                                { rules: [{ required: true, message: '请输入手机号' }, { validator: this.handlephone }], validateTrigger: 'blur' }]"
                            v-if="modify"
                        />
                        <span v-else>{{ userInfo.phoneNumber}}</span>
                    </a-form-item>
                    <a-form-item label="信用值" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <span>{{ userInfo.credit }}</span>
                    </a-form-item>
                    <a-form-item label="密码" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }" v-if="modify">
                        <a-input
                            placeholder="请输入新密码"
                            v-decorator="[
                                'password', 
                                {rules: [{ required: true, message: '请输入新密码' }, { validator: this.handlePassword }], validateTrigger: 'blur'}]">
                            <a-icon slot="prefix" type="lock" :style="{ color: 'rgba(0,0,0,.25)' }"/>
                        </a-input>
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
                            修改信息
                        </a-button>
                    </a-form-item>
                </a-form>
            </a-tab-pane>
            <a-tab-pane tab="我的订单" key="2" >
                <a-table
                    :columns="columns"
                    :dataSource="userOrderList"
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
                    <a-tag slot="orderState" color="blue" slot-scope="text">
                        {{ text }}
                    </a-tag>
                    <span slot="action" slot-scope="record">

                        <a-button type="primary" size="small" @click="showDetail(Array(record))">查看</a-button>
                        <a-divider type="vertical" v-if="record.orderState == '已预订'"></a-divider>
                        <a-divider type="vertical" v-if="record.orderState == '已入住'"></a-divider>
                        <a-divider type="vertical" v-if="record.orderState == '待评价'"></a-divider>
                        <a-button  size="small" v-if="record.orderState == '待评价'" @click="giveMark(record)">评分</a-button>
                        <a-popconfirm
                            title="你确定撤销该笔订单吗？"
                            @confirm="confirmCancelOrder(record.id)"
                            @cancel="cancelCancelOrder"
                            okText="确定"
                            cancelText="取消"
                            v-if="record.orderState == '已预订'"
                        >
                            <a-button type="danger" size="small">撤销</a-button>
                        </a-popconfirm>
                        
                    </span>
                </a-table>
            </a-tab-pane>
            <a-tab-pane tab="信用记录" key=3 >
                <a-table
                    :columns="columns_credit"
                    :dataSource="userCreditList"
                    bordered
                >
                </a-table>
            </a-tab-pane>
        </a-tabs>
        <showDetail></showDetail>
        <grade></grade>
    </div>
</template>
<script>

import { message } from 'ant-design-vue'
import { mapGetters, mapMutations, mapActions } from 'vuex'
import showDetail from '../user/showDetail'
import grade from "../user/grade";
const columns = [
    {  
        title: '订单号',
        dataIndex: 'id',
    },
    {  
        title: '酒店名',
        dataIndex: 'hotelName',
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
        title: '房价',
        dataIndex: 'price',
        scopedSlots: { customRender: 'price' }
    },
    {
        title: '状态',
        filters: [{ text: '已预订', value: '已预订' }, { text: '已撤销', value: '已撤销' }, { text: '已入住', value: '已入住' }],
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

const columns_credit = [
    {  
        title: '订单号',
        dataIndex: 'orderId',
    },
    {
        title: '变化原因',
        dataIndex: 'changeReason',
    },
    {
        title: '变化时间',
        dataIndex: 'recordTimeStr',
    },
    {
        title: '信用值变化',
        dataIndex: 'recordCredit',
    },
    {
        title:'信用值结果',
        dataIndex:'creditResult'
    }
  ];

export default {
    name: 'info',
    data(){
        return {
            modify: false,
            formLayout: 'horizontal',
            pagination: {},
            columns,
            columns_credit,
            data: [],
            form: this.$form.createForm(this, { name: 'coordinated' }),
        }
    },
    components: {
        grade,
        showDetail
    },
    computed: {
        ...mapGetters([
            'userId',
            'userInfo',
            'userOrderList',
            'userCreditList',

        ])
    },
    async mounted() {
        await this.getUserInfo()
        await this.getUserOrders()
        await this.getUserCredits()

    },
    methods: {
        ...mapActions([
            'getUserInfo',
            'getUserOrders',
            'getUserCredits',
            'updateUserInfo',
            'cancelOrder'
        ]),
        ...mapMutations([
            'set_showDetailModalVisible',
            'set_showDetailOrderId',
            'set_showGradeModalVisible',
            'set_showGradeHotelId',
            'set_rateOrderId'
        ]),
        saveModify() {
            this.form.validateFields((err, values) => {
                if (!err) {
                    const data = {
                        userName: this.form.getFieldValue('userName'),
                        phoneNumber: this.form.getFieldValue('phoneNumber'),
                        password: this.form.getFieldValue('password')
                    }
                    this.updateUserInfo(data).then(()=>{
                        this.modify = false
                    })
                }
            });
        },
        modifyInfo() {
            setTimeout(() => {
                this.form.setFieldsValue({
                    'userName': this.userInfo.userName,
                    'phoneNumber': this.userInfo.phoneNumber,
                })
            }, 0)
            this.modify = true
        },
        cancelModify() {
            this.modify = false
        },
        confirmCancelOrder(orderId){
            this.cancelOrder(orderId)
        },
        cancelCancelOrder() {

        },
        showDetail(orderId) {
            this.set_showDetailModalVisible(true)
            this.set_showDetailOrderId(orderId)
        },
        giveMark(e){
            this.set_rateOrderId(e.id)
            this.set_showGradeModalVisible(true)
            this.set_showGradeHotelId(e.hotelId)
            // console.log("hh")
            // console.log()
        },
        handlePassword(rule, value, callback) {
            if (value.length < 6) {
            callback(new Error('密码长度至少6位'))
            }
            callback()
        },
        handlephone(rule, value, callback) {
            if (value.length < 11) {
            callback(new Error('请输入有效的电话号码'))
            }
            callback()
        },
    }
}
</script>
<style scoped lang="less">
    .info-wrapper {
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
    .info-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">
    
</style>
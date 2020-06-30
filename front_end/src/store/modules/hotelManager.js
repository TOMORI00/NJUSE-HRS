import {
    getAllOrdersAPI,
    cancelAbnormalOrderAPI,
} from '@/api/order'
import {
    hotelAllCouponsAPI,
    hotelTargetMoneyAPI,
    hotelTimeAPI
} from '@/api/coupon'
import { message } from 'ant-design-vue'
import  {
    getClientListAPI,
} from '@/api/admin'
import { 
    rechargeCreditAPI,
} from '@/api/user'

const hotelManager = {
    state: {
        orderList: [],
        addRoomParams: {
            roomType: '',
            hotelId: '',
            price: '',
            total: 0,
            curNum: 0,
        },
        addCouponVisible: false,
        activeHotelId: 0,
        couponList: [],
        clientList: [],
    },
    mutations: {
        set_clientList: function(state, data) {
            state.clientList = data
        },
        set_orderList: function(state, data) {
            state.orderList = data.filter(item => item.orderState=="异常")
        },
        set_addRoomModalVisible: function(state, data) {
            state.addRoomModalVisible = data
        },
        set_addRoomParams: function(state, data) {
            state.addRoomParams = {
                ...state.addRoomParams,
                ...data,
            }
        },
        set_couponVisible: function(state, data) {
            state.couponVisible = data
        },
        set_activeHotelId: function(state, data) {
            state.activeHotelId = data
        },
        set_couponList: function(state, data) {
            state.couponList = data
        },
        set_addCouponVisible: function(state, data) {
            state.addCouponVisible =data
        }
    },
    actions: {
        /**
         * @author xyx&&dqj
         * @Description:获得所有的用户信息
         */
        getClientList: async({ state, commit }) => {
            const res = await getClientListAPI()
            if(res){
                commit('set_clientList', res)
            }
        },
        /**
         * @author xyx&&dqj
         * @Description:获得所有的订单信息
         */
        getAllOrders: async({ state, commit }) => {
            const res = await getAllOrdersAPI()
            if(res){
                commit('set_orderList', res)
            }
        },
        /**
         * @author xyx&&dqj
         * @Description:获得优惠策略
         */
        getHotelCoupon: async({ state, commit }) => {
            const res = await hotelAllCouponsAPI(state.activeHotelId)
            if(res) {
                // 获取到酒店策略之后的操作（将获取到的数组赋值给couponList）
                commit('set_couponList',res)
            }
        },
        /**
         * @author xyx&&dqj
         * @Description:添加满减优惠
         */
        addHotelCoupon_target: async({ commit, dispatch }, data) => {
            const res=await hotelTargetMoneyAPI(data);
            if(res){
                // 添加成功后的操作（提示文案、modal框显示与关闭，调用优惠列表策略等）
                dispatch('getHotelCoupon')
                commit('set_addCouponVisible', false)
                commit('set_couponVisible',true)
                message.success('添加策略成功')
            }else{
                // 添加失败后的操作
                message.error('添加失败')
            }
        },
        /**
         * @author xyx&&dqj
         * @Description:添加限时优惠
         */
        addHotelCoupon_time: async({ commit, dispatch }, data) => {
            const res=await hotelTimeAPI(data);
            if(res){
                // 添加成功后的操作（提示文案、modal框显示与关闭，调用优惠列表策略等）
                dispatch('getHotelCoupon')
                commit('set_addCouponVisible', false)
                commit('set_couponVisible',true)
                message.success('添加策略成功')
            }else{
                // 添加失败后的操作
                message.error('添加失败')
            }
        },
        rechargeCredit: async({ commit, dispatch }, data) => {
            // console.log("hi")
            // console.log(data)
            const res = await rechargeCreditAPI(data)
            if(res){
                message.success('充值成功')
                dispatch('getClientList')
            }
        },
        cancelAbnormalOrder: async({commit,dispatch},data)=>{
            const res = await cancelAbnormalOrderAPI(data)
            if(res){
                message.success('恢复成功')
                dispatch('getAllOrders')
            }
        }
    }
}
export default hotelManager
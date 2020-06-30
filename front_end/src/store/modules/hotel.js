import { message } from 'ant-design-vue'
import store from '@/store'
import {
    getHotelsAPI,
    getHotelByIdAPI
} from '@/api/hotel'
import {
    reserveHotelAPI
} from '@/api/order'
import {
    orderMatchCouponsAPI,
} from '@/api/coupon'
import functionCaller from "less/lib/less/functions/function-caller";

const hotel = {
    state: {
        hotelList: [
            
        ],
        hotelListParams: {
            pageNo: 0,
            pageSize: 12
        },
        hotelListLoading: true,
        currentHotelId: '',
        currentHotelInfo: {

        },
        orderModalVisible: false,
        currentOrderRoom: {

        },
        orderMatchCouponList: [

        ],
        star:0
    },
    mutations: {
        /**
         * @author xyx&&dqj
         * @Description:获得所有酒店
         */
        set_hotelList: function(state, data) {
            state.hotelList = data
        },
        /**
         * @author xyx&&dqj
         * @Description:设置酒店参数
         */
        set_hotelListParams: function(state, data) {
            state.hotelListParams = {
                ...state.hotelListParams,
                ...data,
            }
        },
        set_hotelListLoading: function(state, data) {
            state.hotelListLoading = data
        },
        set_currentHotelId: function(state, data) {
            state.currentHotelId = data
        },
        set_currentHotelInfo: function(state, data) {
            state.currentHotelInfo = {
                ...state.currentHotelInfo,
                ...data,
            }
        },
        set_orderModalVisible: function(state, data) {
            state.orderModalVisible = data
        },
        set_currentOrderRoom: function(state, data) {
            state.currentOrderRoom = {
                ...state.currentOrderRoom,
                ...data,
            }
        },
        set_orderMatchCouponList: function(state, data) {
            state.orderMatchCouponList = data
        }
    },

    actions: {
        getHotelList: async({commit, state}) => {
            const res = await getHotelsAPI()
            if(res){
                commit('set_hotelList', res)
                commit('set_hotelListLoading', false)
            }
        },
        /**
         * @author xyx&&dqj
         * @Description:获得选择的酒店信息
         */
        getHotelById: async({commit, state}) => {
            const res = await getHotelByIdAPI({
                hotelId: state.currentHotelId
            })
            if(res){
                commit('set_currentHotelInfo', res)
            }
        },
        /**
         * @author xyx&&dqj
         * @Description:预定酒店
         */
        addOrder: async({ state, commit }, data) => {
            const res = await reserveHotelAPI(data)
            if(res){
                message.success('预定成功')
                commit('set_orderModalVisible', false)
            }
        },
        /**
         * @author xyx&&dqj
         * @Description:获得当前订单匹配的优惠
         */
        getOrderMatchCoupons: async({ state, commit }, data) => {
            const res = await orderMatchCouponsAPI(data)
            if(res){
                commit('set_orderMatchCouponList', res)
            }
        }
    }
}

export default hotel
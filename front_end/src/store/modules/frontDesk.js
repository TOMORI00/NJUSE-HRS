import {
    getHotelOrdersByStaffIdAPI,
    getChargeHotelIdByStaffIdAPI
} from '@/api/frontDesk'
import {message} from 'ant-design-vue'
import {getHotelByIdAPI, updateHotelInfoAPI} from "../../api/hotel";
import {hotelAllCouponsAPI, hotelTargetMoneyAPI, hotelTimeAPI} from "../../api/coupon"
import {addRoomAPI} from "../../api/hotelManager";
import {
    checkInOrderAPI,
    checkOutOrderAPI,
    overdueOrderAPI,
    recheckOrderAPI
} from "../../api/order";

const frontDesk = {
    state: {
        hotelOrderList: [],
        hotelCouponList: [],
        userId: '',
        chargeHotelId: '',
        thisHotelInfo: {},
        addHotelCouponVisible: false,
        addHotelRoomVisible: false,
        addHotelRoomParams: {
            roomType: '',
            hotelId: '',
            price: '',
            total: 0,
            curNum: 0,
        },
    },
    mutations: {
        /**
         * @author xyx&&dqj
         * @Description:获得当前酒店的订单
         */
        set_hotelOrderList: function (state, data) {
            state.hotelOrderList = data
        },
        /**
         * @author xyx&&dqj
         * @Description:设置user的id
         */
        set_userId: function (state, data) {
            state.userId = {
                ...state.userId,
                ...data,
            }
        },
        /**
         * @author xyx&&dqj
         * @Description:获得staff负责的酒店的id
         */
        set_chargeHotelId: function (state, data) {
            state.chargeHotelId = data
        },
        /**
         * @author xyx&&dqj
         * @Description:获得负责的酒店的信息
         */
        set_thisHotelInfo: function (state, data) {
            state.thisHotelInfo = {
                ...state.thisHotelInfo,
                ...data,
            }
        },
        /**
         * @author xyx&&dqj
         * @Description:获得staff负责的酒店的优惠
         */
        set_hotelCouponList: function (state, data) {
            state.hotelCouponList = data
        },
        /**
         * @author xyx&&dqj
         * @Description:设置是否弹出添加优惠的modal
         */
        set_addHotelCouponVisible: function (state, data) {
            state.addHotelCouponVisible = data
        },
        /**
         * @author xyx&&dqj
         * @Description:设置是否弹出添加房间的modal
         */
        set_addHotelRoomVisible: function (state, data) {
            state.addHotelRoomVisible = data
        },
        /**
         * @author xyx&&dqj
         * @Description:设置房间参数
         */
        set_addHotelRoomParams: function (state, data) {
            state.addHotelRoomParams = {
                ...state.addHotelRoomParams,
                ...data,
            }
        },
    },
    actions: {
        /**
         * @author xyx&&dqj
         * @Description:通过酒店staffId得到负责的酒店的订单
         */
        getHotelOrdersByStaffId: async ({state, commit}) => {
            var temp = state.userId
            var num = "";
            for (var item in temp) {
                num += temp[item]
            }
            const data = {
                userId: num
            }
            const res = await getHotelOrdersByStaffIdAPI(data)
            if (res) {
                commit('set_hotelOrderList', res)
            }
        },
        /**
         * @author xyx&&dqj
         * @Description:通过staff的id得到负责的酒店id
         */
        getChargeHotelIdByStaffId: async ({state, commit}) => {
            var temp = state.userId
            var num = "";
            for (var item in temp) {
                num += temp[item]
            }
            const data = {
                userId: num
            }
            const res = await getChargeHotelIdByStaffIdAPI(data)
            if (res) {
                commit('set_chargeHotelId', res)
            }
        },
        /**
         * @author xyx&&dqj
         * @Description:获取当前负责的酒店的信息
         */
        getChargeHotelById: async ({commit, state}) => {
            const res = await getHotelByIdAPI({
                hotelId: state.chargeHotelId
            })
            if (res) {
                commit('set_thisHotelInfo', res)
            }
        },
        /**
         * @author xyx&&dqj
         * @Description:直接获取当前负责的酒店的id
         */
        getChargeHotelIdNum: function (state) {
            return state.chargeHotelId
        },
        /**
         * @author xyx&&dqj
         * @Description:更新当前酒店信息
         */
        updateHotelInfo: async ({state, dispatch}, data) => {
            const params = {
                id: state.chargeHotelId,
                ...data,
            }
            const res = await updateHotelInfoAPI(params)
            if (res) {
                message.success('修改成功')
                dispatch('getChargeHotelIdByStaffId')
                dispatch('getChargeHotelById')
            }
        },
        /**
         * @author xyx&&dqj
         * @Description:获得当前酒店的优惠
         */
        getThisHotelCoupon: async ({state, commit}) => {
            const res = await hotelAllCouponsAPI(state.chargeHotelId)
            if (res) {
                commit('set_hotelCouponList', res)
            }
        },
        /**
         * @author xyx&&dqj
         * @Description:添加满减优惠
         */
        addThisHotelCoupon_target: async ({commit, dispatch}, data) => {
            const res = await hotelTargetMoneyAPI(data);
            if (res) {
                dispatch('getThisHotelCoupon')
                commit('set_addHotelCouponVisible', false)
                message.success('添加策略成功')
            } else {
                message.error('添加失败')
            }
        },
        /**
         * @author xyx&&dqj
         * @Description:添加限时优惠
         */
        addThisHotelCoupon_time: async ({commit, dispatch}, data) => {
            const res = await hotelTimeAPI(data);
            if (res) {
                dispatch('getThisHotelCoupon')
                commit('set_addHotelCouponVisible', false)
                message.success('添加策略成功')
            } else {
                message.error('添加失败')
            }
        },
        /**
         * @author xyx&&dqj
         * @Description:录入房间
         */
        addHotelRoom: async ({state, dispatch, commit}) => {
            const res = await addRoomAPI(state.addHotelRoomParams)
            if (res) {
                commit('set_addHotelRoomVisible', false)
                commit('set_addHotelRoomParams', {
                    roomType: '',
                    hotelId: '',
                    price: '',
                    total: 0,
                    curNum: 0,
                })
                message.success('添加成功')
            } else {

            }
        },
        /**
         * @author xyx&&dqj
         * @Description:登记入住
         */
        checkInOrder: async ({state, dispatch}, orderId) => {
            const res = await checkInOrderAPI(orderId)
            if (res) {
                dispatch('getHotelOrdersByStaffId')
                message.success('登记入住成功')
            } else {
                message.error('登记入住失败')
            }
        },
        /**
         * @author xyx&&dqj
         * @Description:登记退房
         */
        checkoutOrder: async ({state, dispatch}, orderId) => {
            const res = await checkOutOrderAPI(orderId)
            if (res) {
                dispatch('getHotelOrdersByStaffId')
                message.success('登记退房成功')
            } else {
                message.error('登记退房失败')
            }
        },
        /**
         * @author xyx&&dqj
         * @Description:将订单设置成与异常
         */
        overdueOrder: async ({state, dispatch}, orderId) => {
            const res = await overdueOrderAPI(orderId)
            if (res) {
                dispatch('getHotelOrdersByStaffId')
                message.success('置为逾期异常成功')
            } else {
                message.error('置为逾期异失败')
            }
        },
        /**
         * @author xyx&&dqj
         * @Description:延迟入住
         */
        recheckOrder: async ({state, dispatch}, orderId) => {
            const res = await recheckOrderAPI(orderId)
            if (res) {
                dispatch('getHotelOrdersByStaffId')
                message.success('延迟入住成功')
            } else {
                message.error('延迟入住失败')
            }
        },
    }
}
export default frontDesk
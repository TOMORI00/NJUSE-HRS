import Vue from 'vue'
import router from '@/router'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'
import { message } from 'ant-design-vue'
import {
    loginAPI,
    registerAPI,
    getUserInfoAPI,
    updateUserInfoAPI,
    getUserCreditsAPI,
} from '@/api/user'

import {
    getUserOrdersAPI,
    cancelOrderAPI,
} from '@/api/order'
import {hotelRateAPI} from "../../api/hotel";

const getDefaultState = () => {
    return {
        userId: '',
        userInfo: {

        },
        userOrderList: [

        ],
        userCreditList: [

        ],
        showDetailModalVisible: false,
        showDetailOrderId:0,
        //
        showGradeModalVisible: false,
        showGradeHotelId:0
    }
}

const user = {
    state : getDefaultState(),

    mutations: {
        reset_state: function(state) {
            state.token = '',
            state.userId = '',
            state.userInfo = {
                
            },
            state.userOrderList = []
            state.userCreditList = []
            state.showDetailModalVisible = false
            state.showDetailOrderId = 0
            state.showGradeModalVisible=false
        },
        set_token: function(state, token){
            state.token = token
        },
        set_email: (state, data) => {
            state.email = data
        },
        set_userId: (state, data) => {
            state.userId = data
        },
        set_userInfo: (state, data) => {
            state.userInfo = {
                ...state.userInfo,
                ...data
            }
        },
        set_userOrderList: (state, data) => {
            state.userOrderList = data
        },
        set_userCreditList: (state, data) => {
            state.userCreditList = data
        },
        set_showDetailModalVisible: (state,data) => {
            state.showDetailModalVisible = data
        },
        set_showGradeModalVisible: (state,data) => {
            state.showGradeModalVisible = data
        },
        set_showDetailOrderId: (state,data) => {
            state.showDetailOrderId = data
        },
        set_showGradeHotelId: (state,data) => {
            state.showGradeHotelId = data
        }

    },

    actions: {
        login: async ({ dispatch, commit }, userData) => {
            const res = await loginAPI(userData)
            if(res){
                // console.log(res)
                setToken(res.id)
                commit('set_userId', res.id)
                dispatch('getUserInfo')
                router.push('/hotel/hotelList')
            }
        },
        register: async({ commit }, data) => {
            const res = await registerAPI(data)
            if(res){
                message.success('注册成功')
            }
            return res
        },
        getUserInfo({ state, commit }) {
            return new Promise((resolve, reject) => {
              getUserInfoAPI(state.userId).then(response => {
                const data = response
                if (!data) {
                  reject('登录已过期，请重新登录')
                }
                // console.log(data.userType,"==")
                commit('set_userInfo', data)
                commit('set_userId', data.id)
                resolve(data)
              }).catch(error => {
                reject(error)
              })
            })
        },
        updateUserInfo: async({ state, dispatch }, data) => {
            const params = {
                id: state.userId,
                ...data,
            }
            const res = await updateUserInfoAPI(params)
            if(res){
                message.success('修改成功')
                dispatch('getUserInfo')
            }
        },
        getUserOrders: async({ state, commit }) => {
            const data = {
                userId: Number(state.userId)
            }
            const res = await getUserOrdersAPI(data)
            if(res){
                commit('set_userOrderList', res)
            }
        },
        getUserCredits: async({ state, commit }) => {
            const data = {
                userId: Number(state.userId)
            }
            const res = await getUserCreditsAPI(data)
            if(res){
                commit('set_userCreditList', res)
            }
        },
        cancelOrder: async({ state, dispatch }, orderId) => {
            const res = await cancelOrderAPI(orderId)
            if(res) {
                dispatch('getUserOrders')
                dispatch('getUserCredits')
                dispatch('getUserInfo')
                message.success('撤销成功')
            }else{
                message.error('撤销失败')
            }
        },
        logout: async({ commit }) => {
            removeToken()
            resetRouter()
            commit('reset_state')
        },
          // remove token
        resetToken({ commit }) {
            return new Promise(resolve => {
                removeToken() // must remove  token  first
                commit('reset_state')
                resolve()
            })
        },
        showDetail: async({ commit, dispatch }, data) => {
            commit('set_showDetailModalVisible',true)
            commit('set_showDetailOrderId',data)
        },
        /**
         * @author xyx&&dqj
         * @Description:给酒店评分
         */
        rateHotel: async({ commit, dispatch }, data) => {
            const res=await hotelRateAPI(data);
            if(res){
                console.log("success")
                commit("set_showGradeModalVisible",false)
                message.success('评分成功')
            }else{
                console.log("fail")
                message.error('评分失败')
            }
        }

    }
}

export default user
import { axios } from '@/utils/request'
const api = {
    orderPre: '/api/order'
}
export function reserveHotelAPI(data) {
    return axios({
        url: `${api.orderPre}/addOrder`,
        method: 'POST',
        data,
    })
}
export function getAllOrdersAPI() {
    return axios({
        url: `${api.orderPre}/getAllOrders`,
        method: 'GET',
    })
}
export function getUserOrdersAPI(data) {
    return axios({
        url: `${api.orderPre}/${data.userId}/getUserOrders`,
        method: 'GET',
    })
}
export function cancelOrderAPI(orderId) {
    return axios({
        url: `${api.orderPre}/${orderId}/annulOrder`,
        method: 'GET',
    })
}

export function checkInOrderAPI(orderId) {//入住
    return axios({
        url: `${api.orderPre}/${orderId}/checkIn`,
        method: 'GET',
    })
}
export function checkOutOrderAPI(orderId) {//退房
    return axios({
        url: `${api.orderPre}/${orderId}/checkout`,
        method: 'GET',
    })
}
export function overdueOrderAPI(orderId) {//逾期
    return axios({
        url: `${api.orderPre}/${orderId}/overdue`,
        method: 'GET',
    })
}
export function recheckOrderAPI(orderId) {//延迟
    return axios({
        url: `${api.orderPre}/${orderId}/recheck`,
        method: 'GET',
    })
}
export function cancelAbnormalOrderAPI(data) {//延迟
    return axios({
        url: `${api.orderPre}/cancelAbnormalOrder`,
        method: 'POST',
        data,
    })
}






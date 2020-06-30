import { axios } from '@/utils/request'
const api = {
    orderPre: '/api/order'
}
const api_hotel = {
    hotelPre: '/api/hotel'
}
const api_admin = {
    adminPre: '/api/admin'
}
export function getHotelOrdersByStaffIdAPI(data) {
    return axios({
        url: `${api.orderPre}/${data.userId}/getHotelOrdersByStaffId`,
        method: 'GET',
    })
}
export function getChargeHotelIdByStaffIdAPI(data) {
    return axios({
        url: `${api_admin.adminPre}/${data.userId}/getChargeHotelIdByStaffId`,
        method: 'GET',
    })
}

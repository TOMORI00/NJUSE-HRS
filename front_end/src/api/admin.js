import { axios } from '@/utils/request'
const api = {
    adminPre: '/api/admin'
}
const API = {
    hotelPre: '/api/hotel'
}

//Busi相关api
export function getManagerListAPI(){
    return axios({
        url: `${api.adminPre}/getAllBusis`,
        method: 'POST'
    })
}
export function addManagerAPI(data) {
    return axios({
        url: `${api.adminPre}/addBusi`,
        method: 'POST',
        data
    })
}

export function addHotelAPI(data) {
    return axios({
        url: `${API.hotelPre}/addHotel`,
        method: 'POST',
        data,
    })
}

//Staff相关
export function addStaffAPI(data) {
    return axios({
        url: `${api.adminPre}/addStaff`,
        method: 'POST',
        data
    })
}

export function getStaffListAPI(){
    return axios({
        url: `${api.adminPre}/getAllStaffs`,
        method: 'POST'
    })
}

//busi
export function getClientListAPI(){
    return axios({
        url: `${api.adminPre}/getAllClient`,
        method: 'POST'
    })
}
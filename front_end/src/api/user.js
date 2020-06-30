import { axios } from '@/utils/request'

const api = {
    userPre: '/api/user'
}
export function loginAPI(data){
    return axios({
        url:`${api.userPre}/login`,
        method: 'POST',
        data
    })
}
export function registerAPI(data){
    return axios({
        url: `${api.userPre}/register`,
        method: 'POST',
        data
    })
}
export function getUserInfoAPI(id){
    return axios({
        url: `${api.userPre}/${id}/getUserInfo`,
        method: 'GET'
    })
}
export function updateUserInfoAPI(data) {
    return axios({
        url: `${api.userPre}/${data.id}/userInfo/update`,
        method: 'POST',
        data
    })
}
export function getUserCreditsAPI(data) {
    return axios({
        url: `${api.userPre}/${data.userId}/getCreditRecord`,
        method: 'GET',
    })
}
export function rechargeCreditAPI(data) {
    return axios({
        url: `${api.userPre}/rechargeCredit`,
        method: 'POST',
        data,
    })
}

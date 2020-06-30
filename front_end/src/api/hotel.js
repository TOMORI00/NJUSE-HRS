import { axios } from '@/utils/request'
const api = {
    hotelPre: '/api/hotel'
}
export function getHotelsAPI() {
    return axios({
        url: `${api.hotelPre}/all`,
        method: 'get',
    })
}
export function getHotelByIdAPI(param) {
    return axios({
        url: `${api.hotelPre}/${param.hotelId}/detail`,
        method: 'GET',
    })
}

export function hotelRateAPI(data) {
    return axios({
        url: `${api.hotelPre}/changeHotelRate`,
        method: 'POST',
        data,
    })
}
export function updateHotelInfoAPI(data) {
    return axios({
        url: `${api.hotelPre}/${data.id}/hotelInfo/update`,
        method: 'POST',
        data,
    })
}
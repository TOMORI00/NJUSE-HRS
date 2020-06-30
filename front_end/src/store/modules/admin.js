import {
    getManagerListAPI,
    addManagerAPI,
    addHotelAPI,
    getStaffListAPI,
    addStaffAPI,
} from '@/api/admin'
import { message } from 'ant-design-vue'

const admin = {
    state: {
        //添加staff权限
        staffList: [

        ],
        addStaffModalVisible: false,
        addStaffParams: {
            email:'',
            password:''
        },
        //添加busi的权限
        managerList: [

        ],
        addManagerModalVisible: false,
        addManagerParams: {
            email:'',
            password:''
        },
        //下面是添加酒店的权限
        addHotelParams: {
            name: '',
            address: '',
            bizRegion:'XiDan',
            hotelStar:'',
            rate: 0,
            description:'',
            phoneNum:'',
            managerId:'',
        },
        addHotelModalVisible: false,
        activeHotelId: 0
    },
    mutations: {
        //添加staff的权限
        /**
         * @author xyx&&dqj
         * @Description:给酒店工作人员列表赋值
         */
        set_staffList: function(state, data) {
            state.staffList = data
        },
        /**
         * @author xyx&&dqj
         * @Description:设置是否弹出添加酒店工作人员的modal
         */
        set_addStaffModalVisible: function(state, data) {
            state.addStaffModalVisible = data
        },
        /**
         * @author xyx&&dqj
         * @Description:设置添加的酒店工作人员信息
         */
        set_addStaffParams: function(state, data) {
            state.addStaffParams = {
                ...state.addStaffParams,
                ...data,
            }
        },
        //添加busi的权限
        /**
         * @author xyx&&dqj
         * @Description:设置网站营销人员列表
         */
        set_managerList: function(state, data) {
            state.managerList = data
        },
        /**
         * @author xyx&&dqj
         * @Description:设置是否弹出添加网站营销人员modal
         */
        set_addManagerModalVisible: function(state, data) {
            state.addManagerModalVisible = data
        },
        /**
         * @author xyx&&dqj
         * @Description:设置添加的营销人员信息
         */
        set_addManagerParams: function(state, data) {
            state.addManagerParams = {
                ...state.addManagerParams,
                ...data,
            }
        },
        //下面是从原来的manager转移过来的关于添加酒店的权限
        /**
         * @author xyx&&dqj
         * @Description:设置是否弹出添加酒店的modal
         */
        set_addHotelModalVisible: function(state, data) {
            state.addHotelModalVisible = data
        },
        /**
         * @author xyx&&dqj
         * @Description:设置添加的酒店的参数
         */
        set_addHotelParams: function(state, data) {
            state.addHotelParams = {
                ...state.addHotelParams,
                ...data,
            }
        },
        set_activeHotelId: function(state, data) {
            state.activeHotelId = data
        }
    },
    actions: {
        //添加staff权限
        /**
         * @author xyx&&dqj
         * @Description:获得所有的酒店工作人员
         */
        getStaffList: async({ commit }) => {
            const res = await getStaffListAPI()
            if(res){
                commit('set_staffList', res)
            }
        },
        /**
         * @author xyx&&dqj
         * @Description:添加酒店工作人员
         */
        addStaff: async({ state, commit, dispatch }) => {
            const res = await addStaffAPI(state.addStaffParams)
            if(res) {
                commit('set_addStaffParams',{
                    email:'',
                    password:''
                })
                commit('set_addStaffModalVisible', false)
                message.success('添加成功')
                dispatch('getStaffList')
                return true
            }else{
                message.error('添加失败')
                return false
            }
        },
        //添加busi的权限
        /**
         * @author xyx&&dqj
         * @Description:获得所有营销人员
         */
        getManagerList: async({ commit }) => {
            const res = await getManagerListAPI()
            if(res){
                commit('set_managerList', res)
            }
        },
        /**
         * @author xyx&&dqj
         * @Description:添加营销人员
         */
        addManager: async({ state, commit, dispatch }) => {
            const res = await addManagerAPI(state.addManagerParams)
            if(res) {
                commit('set_addManagerParams',{
                    email:'',
                    password:''
                })
                commit('set_addManagerModalVisible', false)
                message.success('添加成功')
                dispatch('getManagerList')
            }else{
                message.error('添加失败')
            }
        },
        /**
         * @author xyx&&dqj
         * @Description:添加酒店
         */
        addHotel: async({ state, dispatch, commit }) => {
            const res = await addHotelAPI(state.addHotelParams)
            if(res){
                dispatch('getHotelList')
                commit('set_addHotelParams', {
                    name: '',
                    address: '',
                    bizRegion:'XiDan',
                    hotelStar:'',
                    rate: 0,
                    description:'',
                    phoneNum:'',
                    managerId:'',
                })
                commit('set_addHotelModalVisible', false)
                message.success('添加成功')
            }else{
                message.error('添加失败')
            }
        },
    }
}
export default admin
<template>
    <div class="manageUser-wrapper">
        <a-tabs>
            <a-tab-pane tab="酒店工作人员管理" key="1">
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="addManager"><a-icon type="plus" />添加酒店工作人员</a-button>
                </div>
                <a-table
                        :columns="columns_staff"
                        :dataSource="staffList"
                        bordered
                ></a-table>
            </a-tab-pane>
            <a-tab-pane tab="网站营销人员管理" key="2">
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="addBusi"><a-icon type="plus" />添加网站营销人员</a-button>
                </div>
                <a-table
                        :columns="columns_busi"
                        :dataSource="managerList"
                        bordered
                >
                </a-table>
            </a-tab-pane>
            <a-tab-pane tab="酒店管理" key="3">
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="addHotel"><a-icon type="plus" />添加酒店</a-button>
                </div>
                <a-table
                        :columns="columns_hotel"
                        :dataSource="hotelList"
                        bordered
                >
                </a-table>
            </a-tab-pane>
        </a-tabs>
        <AddBusiModal></AddBusiModal>
        <AddHotelModal></AddHotelModal>
        <addStaffModal></addStaffModal>
    </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
import AddBusiModal from './components/addBusiModal'
import AddHotelModal from "./components/addHotelModal"
import AddStaffModal from "./components/addStaffModal"
const columns_busi = [
    {  
        title: '用户邮箱',
        dataIndex: 'email',
    },
    {  
        title: '用户名',
        dataIndex: 'userName',
    },
    {
        title: '用户密码',
        dataIndex: 'password',
    },
    {
        title: '用户手机号',
        dataIndex: 'phoneNumber',
    },
  ];
const columns_hotel = [
    {
        title: '酒店名',
        dataIndex: 'name',
    },
    {
        title: '商圈',
        dataIndex: 'bizRegion',
    },
    {
        title: '地址',
        dataIndex: 'address',
    },
    {
        title: '酒店星级',
        dataIndex: 'hotelStar'
    },
    {
        title: '评分',
        dataIndex: 'rate',
    },
    {
        title: '酒店电话',
        dataIndex: 'phoneNum',
    },
    {
        title: '简介',
        dataIndex: 'description',
    },
    {
        title:'酒店工作人员ID',
        dataIndex:'managerId',
    }
];
const columns_staff = [
    {
        title:'用户ID',
        dataIndex:'id',
    },
    {
        title: '用户邮箱',
        dataIndex: 'email',
    },
    {
        title: '用户名',
        dataIndex: 'userName',
    },
    {
        title: '用户密码',
        dataIndex: 'password',
    },
    {
        title: '用户手机号',
        dataIndex: 'phoneNumber',
    },
];
export default {
    name: 'manageHotel',
    data(){
        return {
            formLayout: 'horizontal',
            pagination: {},
            columns_busi,
            columns_hotel,
            columns_staff,
            data: [],
            form: this.$form.createForm(this, { name: 'manageUser' }),
        }
    },
    components: {
        AddHotelModal,
        AddBusiModal,
        AddStaffModal
    },
    computed: {
        ...mapGetters([
            'activeHotelId',
            'hotelList',
            'addHotelModalVisible',
            //
            'addManagerModalVisible',
            'managerList',
            //
            'addStaffModalVisible',
            'staffList'
        ])
    },
    async mounted() {
        await this.getHotelList()
        await this.getManagerList()
        await this.getStaffList()
    },
    methods: {
        ...mapActions([
            'getHotelList',
            'getManagerList',
            'getStaffList'
        ]),
        ...mapMutations([
            'set_addHotelModalVisible',
            'set_activeHotelId',
            'set_addManagerModalVisible',
            'set_addStaffModalVisible'
        ]),
        addBusi(){
            this.set_addManagerModalVisible(true)
        },
        addHotel() {
            this.set_addHotelModalVisible(true)
        },
        addManager(){
            this.set_addStaffModalVisible(true)
        }
    }
}
</script>
<style scoped lang="less">
    .manageUser-wrapper {
        padding: 50px;
        .chart {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-top: 20px
        }
    }
</style>
<style lang="less">
    .manageUser-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">
    
</style>
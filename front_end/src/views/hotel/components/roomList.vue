<template>
    <div class="room-list">
        <div class="filter">

        </div>
        <div class="list">
            <a-table
                :columns="columns"
                :dataSource="rooms"
            >
                <span slot="price" slot-scope="text">
                    <span>￥{{ text }}</span>
                </span>
                <span slot="action" slot-scope="text, record">
                    <a-button type="primary" @click="order(record)">预定</a-button>
                </span>
            </a-table>
        </div>
        <OrderModal></OrderModal>
    </div>
</template>
<script>
import { mapGetters, mapActions, mapMutations } from 'vuex'
import OrderModal from './orderModal'
import { message } from 'ant-design-vue';
const columns = [
    {  
      title: '房型',
      dataIndex: 'roomType',
      key: 'roomType',
    },
    {
      title: '房间数',
      dataIndex: 'total',
      key: 'total',
    },
    {
      title: '剩余房间数',
      dataIndex: 'curNum',
      key: 'curNum',
    },
    {
      title: '房价',
      key: 'price',
      dataIndex: 'price',
      scopedSlots: { customRender: 'price'}
    },
    {
      title: 'Action',
      key: 'action',
      scopedSlots: { customRender: 'action' },
    },
  ];
export default {
    name:'roomList',
    props: {
        rooms: {
            type: Array
        }
    },
    data() {
        return {
            columns,
            
        }
    },
    components: {
        OrderModal
    },
    computed: {
        ...mapGetters([
            'orderModalVisible',
            'userId',
            'userInfo',
            'userCreditList',
        ])
    },
    async mounted() {
        await this.getUserInfo()
        await this.getUserCredits()
    },
    methods: {
        ...mapMutations([
            'set_orderModalVisible',
            'set_currentOrderRoom'
        ]),
        ...mapActions([
            'getUserInfo',
            'getUserCredits',
        ]),
        order(record) {
            //只允许信用值正常的用户预定酒店
            if(this.userInfo.credit < 0){
                message.warning("信用值过低，当前信用为" + (this.userInfo.credit))
                return
            }
            this.set_currentOrderRoom(record)
            this.set_orderModalVisible(true)
        }
    }
}
</script>
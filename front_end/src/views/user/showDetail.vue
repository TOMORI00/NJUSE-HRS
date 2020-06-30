<template>
    <a-modal
        :visible="showDetailModalVisible"
        title="订单详情"
        cancelText="取消"
        okText="确定"
        @cancel="cancel"
        @ok="cancel"
    >
    <a-table
        :columns="columns"
        :dataSource="showDetailOrderId"
        bordered
    >
        <span slot="price" slot-scope="text">
        <span>￥{{ text }}</span>
        </span>
        <span slot="roomType" slot-scope="text">
            <span v-if="text == 'BigBed'">大床房</span>
            <span v-if="text == 'DoubleBed'">双床房</span>
            <span v-if="text == 'Family'">家庭房</span>
        </span>
        <span slot="haveChild" slot-scope="text">
            <span v-if="text == true">有</span>
            <span v-if="text == false">无</span>
        </span>
    </a-table>
    </a-modal>
</template>

<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
const columns = [
    {
        title: '创建时间',
        dataIndex: 'createDate',
    },
    {
        title: '房型',
        dataIndex: 'roomType',
        scopedSlots: { customRender: 'roomType' }
    },
    {
        title: '房间数',
        dataIndex: 'roomNum',
    },
    {
        title: '入住时间',
        dataIndex: 'checkInDate',
        scopedSlots: { customRender: 'checkInDate' }
    },
    {
        title: '离店时间',
        dataIndex: 'checkOutDate',
        scopedSlots: { customRender: 'checkOutDate' }
    },
    {
        title: '入住人数',
        dataIndex: 'peopleNum',
    },
    {
        title: '有无儿童',
        dataIndex: 'haveChild',
        scopedSlots: { customRender: 'haveChild' }
    },
    {
        title: '房价',
        dataIndex: 'price',
        scopedSlots: { customRender: 'price' }
    },
  ];
export default {
    name: 'showDetail',
    data() {
        return {
            formItemLayout: {
                labelCol: {
                    xs: { span: 12 },
                    sm: { span: 6 },
                },
                wrapperCol: {
                    xs: { span: 24 },
                    sm: { span: 16 },
                },
            },
            columns,

        }
    },
    computed: {
        ...mapGetters([
            'showDetailModalVisible',
            'showDetailOrderId',
        ]),
        
    },
    beforeCreate() {
        this.form = this.$form.createForm(this, { name: 'showDetail' });
    },
    methods: {
        ...mapMutations([
            'set_showDetailModalVisible',
            'set_showDetailOrderId'
        ]),
        ...mapActions([
            
        ]),
        cancel() {
            this.set_showDetailModalVisible(false)
        },
    }
}
</script>
<template>
    <a-modal
            :visible="showGradeModalVisible"
            title="给酒店评分"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
    >
        <!-- 这里是添加策略模态框区域，请编写表单 -->
        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
            <a-form-item label="给酒店评分" v-bind="formItemLayout"> <a-input
                    placeholder="请给酒店评分，谢谢"
                    v-decorator="['rate', { rules: [{ required: true, message: '请填写评分' }] }]"
            />
            </a-form-item>
        </a-form>
    </a-modal>
</template>

<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: "grade.vue",
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
            }
        },
        computed: {
            ...mapGetters([
                'showGradeModalVisible',
                'showGradeHotelId',
                'rateOrderId'
            ])
        },
        beforeCreate() {
            // 表单名默认为“form”
            this.form = this.$form.createForm(this, { name: 'rateModal' });
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                'set_showGradeModalVisible'
            ]),
            ...mapActions([
                //评分接口
                'rateHotel'
            ]),
            cancel() {
                this.set_showGradeModalVisible(false)
            },
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {
                            // 这里添加接口参数
                            rate:Number(this.form.getFieldValue('rate')),
                            hotelId:this.showGradeHotelId,
                            orderId:this.rateOrderId

                        }
                        this.rateHotel(data)
                    }
                });
            },
        }
    }
</script>

<style scoped>

</style>
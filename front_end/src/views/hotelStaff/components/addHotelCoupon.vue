<template>
    <a-modal
            :visible="addHotelCouponVisible"
            title="添加优惠策略"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
    >
        <!-- 这里是添加策略模态框区域，请编写表单 -->
        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
            <a-form-item label="优惠券类型" v-bind="formItemLayout">
                <a-select
                        v-decorator="[
                    'type',
                    { rules: [{ required: true, message: '请选择类型' }] }]"
                        @change="changeType"
                >
                    <!-- <a-select-option value="1">生日特惠</a-select-option>
                    <a-select-option value="2">多间特惠</a-select-option> -->
                    <a-select-option value="3">满减特惠</a-select-option>
                    <a-select-option value="4">限时特惠</a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item label="券名" v-bind="formItemLayout"> <a-input
                    placeholder="请填写券名"
                    v-decorator="['name', { rules: [{ required: true, message:'请输入券名' }] }]" />
            </a-form-item>
            <a-form-item label="优惠简介" v-bind="formItemLayout">
                <a-input
                        type="textarea"
                        :rows="4"
                        placeholder="请填写优惠简介"
                        v-decorator="['description', { rules: [{ required: true,message: '请填写优惠简介' }] }]" />
            </a-form-item>
            <a-form-item label="达标金额" v-if="targetmoneyshow">
                <a-input
                        placeholder="请填写达标金额"
                        v-decorator="['targetMoney', { rules: [{ required: false, message: '请填写达标金额' }] }]"
                />
            </a-form-item>
            <a-form-item label="优惠开始时间" v-bind="formItemLayout" v-show="timeshow"><a-input
                    placeholder="请填写优惠开始时间,例如2020/03/21"
                    v-decorator="['starttime', { rules: [{ required: false,message: '请填写优惠开始时间' }] }]" />
            </a-form-item>
            <a-form-item label="优惠结束时间" v-bind="formItemLayout" v-show="timeshow"><a-input
                    placeholder="请填写优惠结束时间,例如2020/03/21"
                    v-decorator="['endtime', { rules: [{ required: false,message: '请填写优惠结束时间' }] }]" />
            </a-form-item>

            <a-form-item label="优惠金额" v-bind="formItemLayout"> <a-input
                    placeholder="请填写优惠金额"
                    v-decorator="['discountMoney', { rules: [{ required: true, message: '请填写优惠金额' }] }]"
            />
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: 'addHotelCoupon',
        data() {
            return {
                targetmoneyshow:false,
                timeshow:false,
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
                'chargeHotelId',
                'addHotelCouponVisible',
            ])
        },
        beforeCreate() {
            // 表单名默认为“form”
            this.form = this.$form.createForm(this, { name: 'addCouponModal' });
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                'set_addHotelCouponVisible'
            ]),
            ...mapActions([
                // addHotelCoupon：添加酒店策略接口
                'addThisHotelCoupon_target',
                'addThisHotelCoupon_time'
            ]),
            cancel() {
                this.set_addHotelCouponVisible(false)
            },
            changeType(v){
                if( v == '3') {
                    this.targetmoneyshow=true;
                    this.timeshow=false
                }else if(v=='4'){
                    this.targetmoneyshow=false;
                    this.timeshow=true
                }else if(v=='1'){
                    this.targetmoneyshow=false;
                    this.timeshow=false
                }
                else if(v=='2'){
                    this.targetmoneyshow=false;
                    this.timeshow=false
                }
                else{
                    this.$message.warning('请实现')
                }
            },
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        if(this.form.getFieldValue('type')=='3'){
                            const data = {
                                // 这里添加接口参数
                                name: this.form.getFieldValue('name'),
                                description: this.form.getFieldValue('description'),
                                type: Number(this.form.getFieldValue('type')),
                                targetMoney: Number(this.form.getFieldValue('targetMoney')),
                                discountMoney: Number(this.form.getFieldValue('discountMoney')),
                                hotelId: Number(this.chargeHotelId),
                                status: 1
                            }
                            this.addThisHotelCoupon_target(data)
                        }
                        if(this.form.getFieldValue('type')=='4'){
                            const data = {
                                // 这里添加接口参数 time
                                name: this.form.getFieldValue('name'),
                                description: this.form.getFieldValue('description'),
                                type: Number(this.form.getFieldValue('type')),
                                discountMoney: Number(this.form.getFieldValue('discountMoney')),
                                startTime:this.form.getFieldValue('starttime'),
                                endTime:this.form.getFieldValue('endtime'),
                                hotelId: Number(this.chargeHotelId),
                                status: 1
                            }
                            this.addThisHotelCoupon_time(data)
                        }

                    }
                });
            },
        }
    }
</script>
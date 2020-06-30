<template>
    <a-modal
            :visible="addStaffModalVisible"
            title="添加用户"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
    >
        <a-Form :form="form">
            <a-form-item v-bind="formItemLayout" label="用户邮箱">
                <a-input
                    type='email'
                    v-decorator="[
                        'email',
                        {rules: [{ required: true, type: 'email', message: '请输入邮箱' }], validateTrigger: 'blur'}
                    ]">
                    <a-icon slot="prefix" type="mail" :style="{ color: 'rgba(0,0,0,.25)' }"/>
                </a-input>
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="密码">
                <a-input
                    v-decorator="[
                        'password',
                        {rules: [{ required: true, message: '请输入密码' }, { validator: this.handlePassword }], validateTrigger: 'blur'}
                    ]">
                    <a-icon slot="prefix" type="lock" :style="{ color: 'rgba(0,0,0,.25)' }"/>
                </a-input>
            </a-form-item >
        </a-Form>
    </a-modal>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: 'addStaffModal',
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
                'addStaffModalVisible',
                'staffList',
            ])
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'addStaffModal' });
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                'set_addStaffModalVisible',
                'set_addStaffParams',
            ]),
            ...mapActions([
                'getStaffList',
                'addStaff',
            ]),
            cancel() {
                this.set_addStaffModalVisible(false)
                this.form.setFieldsValue({
                            'email':'',
                            'password':''
                        })
            },
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {
                            email: this.form.getFieldValue('email'),
                            password: this.form.getFieldValue('password')
                        }
                        this.set_addStaffParams(data)
                        const res=this.addStaff().then(ok=>{
                            console.log(ok)
                            if(ok){
                                this.form.setFieldsValue({
                                    'email':'',
                                    'password':''
                                })
                            }
                        })
                    }
                });
            },
            handlePassword(rule, value, callback) {
                if (value.length < 6) {
                callback(new Error('密码长度至少6位'))
                }
                callback()
            },
        }
    }
</script>
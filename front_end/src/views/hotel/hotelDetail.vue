<template>
    <a-layout>
        <a-layout-content>
            <div class="hotelDetailCard">
                <h1>
                    {{ currentHotelInfo.title }}
                </h1>
                <div class="hotel-info">
                    <a-card style="width: 240px">
                        <img
                        alt="example"
                        src="@/assets/cover.jpeg"
                        slot="cover"
                        referrerPolicy="no-referrer"
                        v-if="currentHotelInfo.id==1"
                        />
                        <img
                        alt="example"
                        src="@/assets/cover2.jpeg"
                        slot="cover"
                        referrerPolicy="no-referrer"
                        v-if="currentHotelInfo.id==2"
                        />
                        <img
                        alt="example"
                        src="@/assets/cover3.jpeg"
                        slot="cover"
                        referrerPolicy="no-referrer"
                        v-if="currentHotelInfo.id==3"
                        />
                        <img
                        alt="example"
                        src="@/assets/cover.jpeg"
                        slot="cover"
                        referrerPolicy="no-referrer"
                        v-if="currentHotelInfo.id!=1 && currentHotelInfo.id!=2 && currentHotelInfo.id!=3"
                        />
                    </a-card>
                    <div class="info">
                        <div class="items" v-if="currentHotelInfo.name">
                            <span class="label">酒店名称：</span>
                            <span class="value">{{ currentHotelInfo.name }}</span>
                        </div>
                        <div class="items" v-if="currentHotelInfo.address">
                            <span class="label">地址</span>
                            <span class="value">{{ currentHotelInfo.address }}</span>
                        </div>
                        <div class="items" v-if="currentHotelInfo.rate">
                            <span class="label">评分:</span> 
                            <span class="value">{{ currentHotelInfo.rate.toFixed(2) }}</span>
                        </div>
<!--                        <div class="items" v-if="currentHotelInfo.hotelStar">-->
<!--                            <span class="label">星级:</span> -->
<!--                            <a-rate style="font-size: 15px" :value="currentHotelInfo.rate" disabled allowHalf/>-->
<!--                        </div>-->
                        <div class="items" v-if="currentHotelInfo.hotelStar">
                            <span class="label">星级:</span>
                            <a-rate v-if="currentHotelInfo.hotelStar=='Five'" style="font-size: 15px" :value="5" disabled allowHalf/>
                            <a-rate v-if="currentHotelInfo.hotelStar=='Four'" style="font-size: 15px" :value="4" disabled allowHalf/>
                            <a-rate v-if="currentHotelInfo.hotelStar=='Three'" style="font-size: 15px" :value="3" disabled allowHalf/>
                            <a-rate v-if="currentHotelInfo.hotelStar=='Two'" style="font-size: 15px" :value="2" disabled allowHalf/>
                            <a-rate v-if="currentHotelInfo.hotelStar=='One'" style="font-size: 15px" :value="1" disabled allowHalf/>
                        </div>
                        <div class="items" v-if="currentHotelInfo.description">
                            <span class="label">酒店简介:</span> 
                            <span class="value">{{ currentHotelInfo.description }}</span>
                        </div>
                    </div>
                </div>
                <a-divider></a-divider>
                <a-tabs>
                    <a-tab-pane tab="房间信息" key="1">
                        <RoomList :rooms="currentHotelInfo.rooms"></RoomList>
                    </a-tab-pane>
                    <!-- <a-tab-pane tab="酒店详情" key="2">

                    </a-tab-pane> -->
                </a-tabs>
            </div>
        </a-layout-content>
    </a-layout>
</template>
<script>
import { mapGetters, mapActions, mapMutations } from 'vuex'
import RoomList from './components/roomList'
export default {
    name: 'hotelDetail',
    components: {
        RoomList,
    },
    data() {
        return {
            rate:0,
            Star:0
        }
    },
    computed: {
        ...mapGetters([
            'currentHotelInfo',
        ])
    },
    mounted() {
        this.set_currentHotelId(Number(this.$route.params.hotelId))
        this.getHotelById()
    },
    beforeRouteUpdate(to, from, next) {
        this.set_currentHotelId(Number(to.params.hotelId))
        this.getHotelById()
        next()
    },
    methods: {
        ...mapMutations([
            'set_currentHotelId',
        ]),
        ...mapActions([
            'getHotelById'
        ]),
    }
}
</script>
<style scoped lang="less">
    .hotelDetailCard {
        padding: 50px 50px;
    }
    .hotel-info {
        display: flex;
        align-items: stretch;
        justify-content: flex-start;
        .info{
            padding: 10px 0;
            display: flex;
            flex-direction: column;
            margin-left: 20px;
            .items {
                display: flex;
                align-items: center;
                margin-bottom: 10px;
                .label{
                    margin-right: 10px;
                    font-size: 18px;
                }
                .value {
                    margin-right: 15px
                }
            }
        }
    }
</style>
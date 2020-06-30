<template>
  <a-card hoverable class="hotelCard ant-col-xs-7 ant-col-lg-5 ant-col-xxl-3">
    <img
      alt="example"
      src="@/assets/cover.jpeg"
      slot="cover"
      referrerPolicy="no-referrer"
      v-if="hotel.id==1"
    />
    <img
      alt="example"
      src="@/assets/cover2.jpeg"
      slot="cover"
      referrerPolicy="no-referrer"
      v-if="hotel.id==2"
    />
    <img
      alt="example"
      src="@/assets/cover3.jpeg"
      slot="cover"
      referrerPolicy="no-referrer"
      v-if="hotel.id==3"
    />
    <img
      alt="example"
      src="@/assets/cover.jpeg"
      slot="cover"
      referrerPolicy="no-referrer"
      v-if="hotel.id!=1 && hotel.id!=2 && hotel.id!=3"
    />
    <a-tooltip :title="hotel.title" placement="top">
      <a-card-meta :title="hotel.name">
      <template slot="description">
        <a-rate style="font-size: 15px" :value="Star" disabled allowHalf/> 
        <div v-if="hotel.rate != 0">
        {{hotel.rate.toFixed(2)}}分
        </div>
        <div v-if="hotel.rate == 0">
          暂无评分
        </div>
      </template>
    </a-card-meta>
    </a-tooltip>
  </a-card>
</template>
<script>
export default {
  name:'',
  props: {
    hotel: {}
  },
  data() {
    return{
      Star:0
    }
  },
  mounted() {
    this.trans()
  },
  methods:{
    trans(){
      if(this.hotel.hotelStar=="Four"){
        this.Star=4;
      }
      if(this.hotel.hotelStar=="One"){
        this.Star=1;
      }
      if(this.hotel.hotelStar=="Two"){
        this.Star=2;
      }
      if(this.hotel.hotelStar=="Three"){
        this.Star=3;
      }
      if(this.hotel.hotelStar=="Five"){
        this.Star=5;
      }
    }
  }
}
</script>
<style scoped lang="less">
    .hotelCard {
        margin: 10px 10px;
        min-width: 180px;
        max-height: 350px;
        img {
          height: 250px;
        }
    }
</style>

<style lang="less">
.hotelCard{
  .ant-card-body{
    padding: 12px;
  }
}

.ant-card-hoverable:hover {
  box-shadow: 0 30px 100px rgba(0,0,0,0.4)
}

</style>
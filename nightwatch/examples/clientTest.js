module.exports = {
    'modifyInfo': function (browser) {
      let that=this
      var num=Math.random()
      num=num*100
      num=Math.floor(num)
      var numStr=num.toString()
      var username;
      browser
        .url('http://localhost:8000/')
        .setValue('#username','123@qq.com')
        .setValue('#password','123456')
        .click('#formLogin > div > div.ant-tabs-content.ant-tabs-content-animated.ant-tabs-top-content > div.ant-tabs-tabpane.ant-tabs-tabpane-active > div:nth-child(4) > div > div > span > button')
        .click('#layout > div.header > ul > li:nth-child(4)')
        .click('#layout > div.info-wrapper > div > div.ant-tabs-content.ant-tabs-content-animated.ant-tabs-top-content > div.ant-tabs-tabpane.ant-tabs-tabpane-active > form > div:nth-child(5) > div > div > span > button')
        .setValue('#coordinated_password','123456')
        .getValue('#coordinated_userName',function(result){
          console.log(result.value)
          username=result.value
          console.log(username+numStr)
        })
        .setValue('#coordinated_userName',numStr)
        .click('#layout > div.info-wrapper > div > div.ant-tabs-content.ant-tabs-content-animated.ant-tabs-top-content > div.ant-tabs-tabpane.ant-tabs-tabpane-active > form > div:nth-child(6) > div > div > span > button.ant-btn.ant-btn-primary')
        .assert.containsText('#layout > div.info-wrapper > div > div.ant-tabs-content.ant-tabs-content-animated.ant-tabs-top-content > div.ant-tabs-tabpane.ant-tabs-tabpane-active > form > div:nth-child(2) > div.ant-col.ant-col-8.ant-col-offset-1.ant-form-item-control-wrapper > div > span > span','123@qq.com')
        .end()
    },
    "hotelDetail1":function(browser){
      browser
      .url('http://localhost:8000/')
      .setValue('#username','123@qq.com')
      .setValue('#password','123456')
      .click('#formLogin > div > div.ant-tabs-content.ant-tabs-content-animated.ant-tabs-top-content > div.ant-tabs-tabpane.ant-tabs-tabpane-active > div:nth-child(4) > div > div > span > button')
      .click('#layout > div.hotelList > section > main > div > div > div > div:nth-child(1)')
      .assert.containsText('#layout > section > main > div > div.hotel-info > div.info > div:nth-child(1) > span.value','汉庭酒店')
      .end()
    }
}
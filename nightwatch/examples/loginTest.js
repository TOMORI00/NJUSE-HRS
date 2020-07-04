module.exports = {
    'login': function (browser) {
      browser
        .url('http://localhost:8000/')
        .setValue('#username','333@qq.com')
        .setValue('#password','123456')
        .click('#formLogin > div > div.ant-tabs-content.ant-tabs-content-animated.ant-tabs-top-content > div.ant-tabs-tabpane.ant-tabs-tabpane-active > div:nth-child(4) > div > div > span > button')
        .assert.urlEquals('http://localhost:8000/#/hotel/hotelList')
        .end(); 
    },
    'loginNotok':function(browser){
        browser
        .url('http://localhost:8000/')
        .setValue('#username','notExist@qq.com')
        .setValue('#password','123456')
        .click('#formLogin > div > div.ant-tabs-content.ant-tabs-content-animated.ant-tabs-top-content > div.ant-tabs-tabpane.ant-tabs-tabpane-active > div:nth-child(4) > div > div > span > button')
        .assert.urlEquals('http://localhost:8000/#/login?redirect=%2Fhotel%2FhotelList')
        .end();
    }
  };
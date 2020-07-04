module.exports = {
    src_folders: ['examples'],//这边的src_folders的值为运行的测试目录
    output_folder: 'output',
    custom_assertions_path: [],
    page_objects_path: '',
    globals_path: '',
   
    selenium: {
      start_process: true,
      server_path: require('selenium-server').path,
      host: '127.0.0.1',
      port: 5555,
      cli_args: {
        'webdriver.chrome.driver': require('chromedriver').path
      }
    },
   
    test_settings: {
      default: {
        selenium_port: 5555,
        selenium_host: 'localhost',
        silent: true,
        globals: {
          devServerURL: 'http://localhost:' + (process.env.PORT || 1111)
        }
      },
   
      chrome: {
        desiredCapabilities: {
          browserName: 'chrome',
          javascriptEnabled: true,
          acceptSslCerts: true,
          chromeOptions: {
            "w3c": false
        }
        }
      },
   
      firefox: {
        desiredCapabilities: {
          browserName: 'firefox',
          javascriptEnabled: true,
          acceptSslCerts: true
        }
      }
    }
  }
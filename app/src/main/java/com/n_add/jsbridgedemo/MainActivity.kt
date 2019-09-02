package com.n_add.jsbridgedemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import com.github.lzyzsd.jsbridge.CallBackFunction
import com.github.lzyzsd.jsbridge.BridgeHandler




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView.loadUrl("http://192.168.98.68:8080/html/page")

        java_to_js_default.setOnClickListener {
            //默认接收
            webView.send("发送数据给js默认接收") { data ->
                //处理js回传的数据
                Toast.makeText(this@MainActivity, data, Toast.LENGTH_LONG).show()
            }
        }

        java_to_js_spec.setOnClickListener {
            //指定接收参数 functionInJs
            webView.callHandler("functionInJs", "发送数据给js指定接收") { data ->
                //处理js回传的数据
                Toast.makeText(this@MainActivity, data, Toast.LENGTH_LONG).show()
            }
        }

        //默认接收
        webView.setDefaultHandler { data, function ->
            val msg = "默认接收到js的数据：$data"
            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()

            function.onCallBack("java默认接收完毕，并回传数据给js") //回传数据给js
        }
        //指定接收 submitFromWeb 与js保持一致
        webView.registerHandler("submitFromWeb") { data, function ->
            val msg = "指定接收到js的数据：$data"
            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()

            function.onCallBack("java指定接收完毕，并回传数据给js") //回传数据给js
        }
    }
}

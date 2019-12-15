package com.chartapp.rest.hello

import io.swagger.annotations.ApiModelProperty

data class HelloEntity (
        @ApiModelProperty(value = "言葉", required = true)
        //APIのモデルに関する情報を設定できる。
        val word: String = ""
)
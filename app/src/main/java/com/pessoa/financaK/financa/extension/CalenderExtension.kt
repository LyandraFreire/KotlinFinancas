package com.pessoa.financaK.financa.extension

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.formataBrasileiro(): String {
    val formatoBrasileiro = SimpleDateFormat("dd/MM/yyyy")
    return formatoBrasileiro.format(this.time)

}
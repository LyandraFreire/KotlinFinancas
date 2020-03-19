package com.pessoa.financaK.financa.model

import java.math.BigDecimal
import java.util.Calendar

class Transacao(
    val valor: BigDecimal,
    val categoria: String = "Indefinidade",
    val tipo: Tipo,
    val data: Calendar = Calendar.getInstance()
)
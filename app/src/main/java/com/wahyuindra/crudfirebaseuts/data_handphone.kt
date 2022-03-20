package com.wahyuindra.crudfirebaseuts

class data_handphone {

    var nama : String? = null
    var merk : String? = null
    var chipset : String? = null
    var RAM : String? = null
    var harga : String? = null
    var key: String? = null

    constructor() {}

    constructor(nama: String?, merk: String?, chipset: String?, RAM: String?, harga: String?) {
        this.nama = nama
        this.merk = merk
        this.chipset = chipset
        this.RAM = RAM
        this.harga = harga

    }

}




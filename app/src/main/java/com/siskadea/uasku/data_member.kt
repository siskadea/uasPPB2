package com.siskadea.uasku

class data_member {

    //Deklarasi Variable
    var nama: String? = null
    var webku: String? = null
    var nohp: String? = null
    var email: String? = null
    var alamat: String? = null
    var key: String? = null
    //Membuat Konstuktor kosong untuk membaca data snapshot
    constructor() {}
    //Konstruktor dengan beberapa parameter, untuk mendapatkan Input Data dari User

    constructor(nama: String?, webku: String?, nohp: String?, email: String?, alamat: String?) {
        this.nama = nama
        this.webku = webku
        this.nohp = nohp
        this.email = email
        this.alamat = alamat
    }
}
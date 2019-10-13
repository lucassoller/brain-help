export default class FormatarData{
    static formatar(dataDeEnvio){
        var datas = dataDeEnvio.split("T")
        var data = datas[0].split("-")
        var dia = data[2]
        var mes = data[1]
        var ano = data[0]
        var hora = datas[1].split(".")[0]
        return dia+"/"+mes+"/"+ano + " " + hora.substring(0,5)
    }
}
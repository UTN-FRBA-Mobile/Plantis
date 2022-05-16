package ar.utn.frba.mobile.plantis.client

interface PlantIdentifierAPI {
    fun identifyPlantFromImage(filePath: String): List<PlantIdentification>
    //Reconocer enfermedad(file)
}
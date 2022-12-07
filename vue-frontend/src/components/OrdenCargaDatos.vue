<template>
    <div class="container" v-if="!requestCompleteOrdenCarga">
        <table>
            <tr>
                <td>Token Generated:</td>
                <td><input id="input_token" type="text" v-model="token" /></td>
            </tr>
            <tr>
                <td>ID Orden:</td>
                <td><input type="text" v-model="cargaDatos.id" /></td>
            </tr>
            <tr>
                <td>Masa acumulada:</td>
                <td><input type="text" v-model="cargaDatos.masaAcumulada" /></td>
            </tr>
            <tr>
                <td>Densidad del producto:</td>
                <td><input type="text" v-model="cargaDatos.densidadProducto" /></td>
            </tr>
            <tr>
                <td>Temperatura del producto:</td>
                <td><input type="text" v-model="cargaDatos.temperaturaProducto" /></td>
            </tr>
            <tr>
                <td>Caudal:</td>
                <td><input type="text" v-model="cargaDatos.caudal" /></td>
            </tr>
            <tr>
                <td><button @click="addCargaDatos">Cargar Datos</button></td>
            </tr>
        </table>
    </div>

    <div v-if="requestCompleteOrdenCarga">
        <div class="alert alert-success" role="alert">
            <i class="fas fa-check-circle"></i> La petición se completó exitosamente
        </div>
    </div>
        <b-alert variant="danger" show class="alert-dismissible fade">
            La temperatura ingresada es mayor a 60 grados.
            <b-button size="sm" variant="danger" class="close" @click="showAlert = false">&times;</b-button>
        </b-alert>

</template>

<script>
import OrdenService from '@/services/OrdenService';


export default {
    name: '',
    data() {
        return {
            ordenes: [],
            token: '',
            requestCompleteOrdenCarga: false,
            id: '',
            cargaDatos: {
                "masaAcumulada": 100,
                "densidadProducto": 0.4,
                "temperaturaProducto": 60.8,
                "caudal": 14
            }

        }
    },
    methods: {
        async addCargaDatos() {
            try {

                // Obtener la orden especificada
                //const orden = await OrdenService.getOrdenesById(this.cargaDatos.id, this.token);

                // Comparar la temperatura ingresada con la temperatura máxima de la orden
                //if (this.cargaDatos.temperaturaProducto > orden.ultimosDatosCarga.temperaturaMax) {
                if (this.cargaDatos.temperaturaProducto > 60) {
                    this.showAlert = true;
                    console.log(this.showAlert)
                } else {
                    this.showAlert = false;
                    console.log(this.showAlert)
                }


                this.ordenes = await OrdenService.addCargaDatos(this.cargaDatos.id, this.cargaDatos, this.token)
                this.requestCompleteOrdenCarga = true;
            } catch (error) {
                console.error(error);
            }
        }
    },

}

</script>

<style>

</style>
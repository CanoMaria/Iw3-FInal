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
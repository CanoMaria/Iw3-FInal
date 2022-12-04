<template>

    <input id="input_token" type="text" v-model="token" v-if="!requestComplete" />
    <button id="id_button_ordenes" @click="getOrdenes" v-if="!requestComplete">
        Get ordenes
    </button>
    <div class="container" v-if="ordenes.length > 0">
        <h1 class="text-center"> Lista de Ordenes</h1>

        <table class="table table-striped table-bordered">
            <thead>
                <th>Orden ID</th>
                <th>Orden Fecha Recepcion</th>
                <th>Orden Fecha Recepcion Pesaje Inicial</th>
                <th>Orden Fecha Fin de Carga</th>

                <th>Datos del Cliente asociado a la orden</th>

                <th>Datos del Producto asociado a la orden</th>

                <th>Datos del Camion asociado a la orden</th>

                <th>Datos del Chofer asociado a la orden</th>

            </thead>
            <tbody>
                <tr v-for="orden in ordenes" v-bind:key="orden.id">
                    <td> {{ orden.id }} </td>
                    <td> {{ orden.fechaRecepcion }} </td>
                    <td> {{ orden.fechaRecepcionPesajeInicial }} </td>
                    <td> {{ orden.fechaFinCarga }} </td>
                    <td> {{ orden.cliente }} </td>
                    <td> {{ orden.producto }} </td>
                    <td> {{ orden.camion }} </td>
                    <td> {{ orden.chofer }} </td>

                </tr>
            </tbody>
        </table>

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
            requestComplete: false,
        }
    },
    methods: {
        async getOrdenes() {
            try {
                // Obtener un token llamando al método "getTokens" importado

                // Llamar al método "getCamiones" del servicio de camión, pasando el token como un parámetro
                this.ordenes = await OrdenService.getOrdenes(this.token);
                this.requestComplete = true;
            } catch (error) {
                console.error(error);
            }
        }
    },
    // created(){
    //      this.getCamiones()
    // }

}

</script>

<style>

</style>
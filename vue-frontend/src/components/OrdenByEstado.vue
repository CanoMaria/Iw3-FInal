<template>

    <div v-if="!requestCompleteOrdenesByEstado">
        Token Generated:<input id="input_token" type="text" v-model="token"  />
        Estado de la Orden:<input type="text" v-model="estadoInput" />
        <button id="id_button_ordenes_by_estado" @click="getOrdenes">
            Get ordenes
        </button>
    </div>

    <div class="container" v-if="ordenes.length > 0">
        <h1 class="text-center"> Orden filtro de Estado</h1>

        <table class="table table-striped table-bordered">
            <thead>
                <th>ID</th>
                <th>Fecha Recepcion</th>
                <th>Fecha Recepcion Pesaje Inicial</th>
                <th>Fecha Fin de Carga</th>

                <th>[Cliente]Razon social</th>
                <th>[Cliente]Contacto</th>


                <th>[Producto]Nombre</th>

                <th>[Camion]Patente</th>

                <th>[Camion]Cisternado</th>


                <th>[Chofer]Nombre</th>
                <th>[Chofer]Apellido</th>
                <th>[Chofer]Documento</th>


                <th>Estado de la orden</th>


            </thead>
            <tbody>
                <tr v-for="orden in ordenes.filter(orden => orden.estado == estadoInput)" v-bind:key="orden.id">
                    <td> {{ orden.id }} </td>
                    <td> {{ orden.fechaRecepcion }} </td>
                    <td> {{ orden.fechaRecepcionPesajeInicial }} </td>
                    <td> {{ orden.fechaFinCarga }} </td>

                    <td> {{ orden.cliente.razonSocial }} </td>
                    <td> {{ orden.cliente.contacto }} </td>


                    <td> {{ orden.producto.nombre }} </td>

                    <td> {{ orden.camion.patente }} </td>
                    <td> {{ orden.camion.cisternado }} </td>


                    <td> {{ orden.chofer.nombre }} </td>
                    <td> {{ orden.chofer.apellido }} </td>
                    <td> {{ orden.chofer.documento }} </td>


                    <td> {{ orden.estado }} </td>


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
            requestCompleteOrdenesByEstado: false,
            id: '',
            estadoInput: '',

        }
    },
    methods: {
        async getOrdenes() {
            try {
                this.ordenes = await OrdenService.getOrdenes(this.token)
                this.requestCompleteOrdenesByEstado = true;
            } catch (error) {
                console.error(error);
            }
        }
    },

}

</script>

<style>

</style>
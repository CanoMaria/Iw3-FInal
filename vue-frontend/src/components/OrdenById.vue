<template>

    <div v-if="!requestCompleteOrdenesById">
        Token Generated:<input id="input_token" type="text" v-model="token"  />
        Estado Search:<input id="input_id" type="text" v-model="id" />

        <button id="id_button_ordenes_by_id" @click="getOrdenesById">
            Search Orden by ID
        </button>
    </div>


    <div class="container" v-if="ordenes.length > 0">
        <h1 class="text-center"> Orden filtro de ID</h1>

        <table class="table table-striped table-bordered">
            <thead>
                <th>ID</th>
                <th>[Cliente]Razon social</th>
                <th>[Cliente]Contacto</th>
                <th>[Producto]Nombre</th>
                <th>[Camion]Patente</th>
                <th>[Camion]Cisternado</th>
                <th>[Peso]Inicial</th>
                <th>[Peso]Final</th>
                <th>[Chofer]Nombre</th>
                <th>[Chofer]Apellido</th>
                <th>[Chofer]Documento</th>
                <th>Estado de la orden</th>


            </thead>
            <tbody>
                <tr v-for="orden in ordenes" v-bind:key="orden.id">
                    <td> {{ orden.id }} </td>

                    <td> {{ orden.cliente.razonSocial }} </td>
                    <td> {{ orden.cliente.contacto }} </td>
                    <td> {{ orden.producto.nombre }} </td>
                    <td> {{ orden.camion.patente }} </td>
                    <td> {{ orden.camion.cisternado }} </td>
                    <td> {{ orden.pesoInicial }} </td>
                    <td> {{ orden.pesoFinal}} </td>
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
            requestCompleteOrdenesById: false,
            id: '',

        }
    },
    methods: {
        async getOrdenesById() {
            try {
                // Obtener un token llamando al método "getTokens" importado

                // Llamar al método "getOrdenesById" del servicio de orden, pasando el id y el token como parámetros
                this.ordenes = [await OrdenService.getOrdenesById(this.id, this.token)]
                this.requestCompleteOrdenesById = true;
            } catch (error) {
                console.error(error);
            }
        }
    },

}

</script>

<style>

</style>
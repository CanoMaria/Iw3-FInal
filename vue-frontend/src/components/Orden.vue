<template>
    <button id="id_button_ordenes" @click="getOrdenes" v-if="ordenes.length<=0">
      Listar ordenes
    </button>
    <div class="container" v-if="ordenes.length>0">
        <h1 class="text-center"> Lista de Ordenes</h1>
        <table class="table table-striped">
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
                <tr v-for = "orden in ordenes" v-bind:key = "orden.id">
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

    export default{
        name: '',
        data(){
            return {
                ordenes : []
            }
        },
        methods:{
            getOrdenes(){
                OrdenService.getOrdenes().then((response) => {
                    this.ordenes = response.data
                })
            }
        },
        // created(){
        //      this.getCamiones()
        // }

    }

</script>

<style>

</style>
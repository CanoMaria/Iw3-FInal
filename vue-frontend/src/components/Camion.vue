<template>
    <button id="id_button_camiones" @click="getCamiones"  v-if="camiones.length<=0">
      Traer camiones
    </button>
    <div class="container" v-if="camiones.length>0">
        <h1 class="text-center"> Lista de Camiones</h1>
        <table class="table table-striped">
            <thead>
                <th>Camion ID</th>
                <th>Camion Patente</th>
                <th>Camion Descripcion</th>
                <th>Camion Cisternado</th>
            </thead>
            <tbody>
                <tr v-for = "camion in camiones" v-bind:key = "camion.id">
                <td> {{ camion.id }} </td>
                <td> {{ camion.patente }} </td>
                <td> {{ camion.descripcion }} </td>
                <td> {{ camion.cisternado }} </td>
                </tr>
            </tbody>
        </table>

    </div>
</template>

<script>
import CamionService from '@/services/CamionService';

    export default{
        name: '',
        data(){
            return {
                camiones : []
            }
        },
        methods:{
            getCamiones(){
                CamionService.getCamiones().then((response) => {
                    this.camiones = response.data
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
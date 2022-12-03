<template>
    <button id="id_button_choferes" @click="getChoferes">
      Listar choferes
    </button>
    <div class="container" v-if="choferes.length>0">
        <h1 class="text-center"> Lista de Choferes</h1>
        <table class="table table-striped">
            <thead>
                <th>Chofer ID</th>
                <th>Chofer Nombre</th>
                <th>Chofer Apellido</th>
                <th>Chofer Documento</th>
            </thead>
            <tbody>
                <tr v-for = "chofer in choferes" v-bind:key = "chofer.id">
                <td> {{ chofer.id }} </td>
                <td> {{ chofer.nombre }} </td>
                <td> {{ chofer.apellido }} </td>
                <td> {{ chofer.documento }} </td>
                </tr>
            </tbody>
        </table>

    </div>
</template>

<script>
import ChoferService from '@/services/ChoferService';

    export default{
        name: '',
        data(){
            return {
                choferes : []
            }
        },
        methods:{
            getChoferes(){
                ChoferService.getChoferes().then((response) => {
                    this.choferes = response.data
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
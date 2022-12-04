<template>
    <div></div>
    <input id="input_token" type="text" v-model="token" v-if="!requestComplete"/>
    <button id="id_button_camiones" @click="getCamiones" v-if="!requestComplete">
        Get camiones
    </button>
    <div class="container" v-if="camiones.length > 0">
        <h1 class="text-center"> Lista de Camiones</h1>
        <table class="table table-striped">
            <thead>
                <th>Camion ID</th>
                <th>Camion Patente</th>
                <th>Camion Descripcion</th>
                <th>Camion Cisternado</th>
            </thead>
            <tbody>
                <tr v-for="camion in camiones" v-bind:key="camion.id">
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

export default {
    name: '',

    data() {
        return {
            camiones: [],
            token: '',
            requestComplete: false,

        };
    },
    computed: {
  requestComplete() {
    return this.requestComplete;
  },
},
    methods: {
        async getCamiones() {
            try {
                // Obtener un token llamando al método "getTokens" importado

                // Llamar al método "getCamiones" del servicio de camión, pasando el token como un parámetro
                this.camiones = await CamionService.getCamiones(this.token);
                this.requestComplete = true;
            } catch (error) {
                console.error(error);
            }
        }
    }
};
    // created(){
    //      this.getCamiones()
    // }

</script>

<style>

</style>
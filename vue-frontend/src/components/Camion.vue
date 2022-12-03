<template>
    <button id="id_button_camiones" @click="getCamiones">
        Listar camiones
    </button>
    <div>
        <input id="input_token" type="text" v-model="token"/>
    </div>
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

// Importar la clase Token
import TokenService from '@/services/TokenService';



export default {
    name: '',

    data() {
        return {
            camiones: [],
            token: ''
        };
    },
    methods: {
        async getCamiones() {
            try {
                // Obtener un token llamando al método "getTokens" importado
                //const token = await TokenService.getTokens();

                // Llamar al método "getCamiones" del servicio de camión, pasando el token como un parámetro
                const camiones = await CamionService.getCamiones(token);
                this.camiones = camiones;
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
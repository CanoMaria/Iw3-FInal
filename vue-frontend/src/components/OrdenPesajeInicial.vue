<template>
    <div class="container" v-if="!requestCompleteOrdenesPesajeInicial">
        <table>
            <tr>
                <td>Token Generated:</td>
                <td><input id="input_token" type="text" v-model="token" /></td>
            </tr>
            <tr>
                <td>ID Orden:</td>
                <td><input type="text" v-model="pesajeInicial.id" /></td>
            </tr>
            <tr>
                <td>Peso Inicial:</td>
                <td><input type="text" v-model="pesajeInicial.pesoInicial" /></td>
            </tr>
            <tr>
                <td><button @click="addPesajeInicial">Ingresar Peso Inicial</button></td>
            </tr>
        </table>
    </div>
    
    <div>
    <div v-if="requestCompleteOrdenesPesajeInicial">
      <div class="alert alert-success" role="alert">
        <i class="fas fa-check-circle"></i> La petición se completó exitosamente
      </div>
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
            requestCompleteOrdenesPesajeInicial: false,
            pesajeInicial: {
                "id": '',
                "pesoInicial": ''
            }

        }
    },
    methods: {
        async addPesajeInicial() {
            try {
                this.ordenes = await OrdenService.addPesajeInicial(this.pesajeInicial,this.token)
                this.requestCompleteOrdenesPesajeInicial = true;
            } catch (error) {
                console.error(error);
            }
        }
    },

}

</script>

<style>

</style>
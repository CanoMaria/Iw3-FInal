<template>
    <div class="container" v-if="!requestCompleteOrdenesPesajeFinal" >
        <table>
            <tr>
                <td>Token Generated:</td>
                <td><input id="input_token" type="text" v-model="token" /></td>
            </tr>
            <tr>
                <td>ID Orden:</td>
                <td><input type="text" v-model="pesajeFinal.id" /></td>
            </tr>
            <tr>
                <td>Peso Final:</td>
                <td><input type="text" v-model="pesajeFinal.pesoFinal" /></td>
            </tr>
            <tr>
                <td><button @click="addPesajeFinal">Ingresar Peso Final</button></td>
            </tr>
        </table>
    </div>
    
    
    <div v-if="requestCompleteOrdenesPesajeFinal">
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
            requestCompleteOrdenesPesajeFinal: false,
            id: '',
            pesajeFinal: {
                "id": 362,
                "pesoFinal": 777
            }

        }
    },
    methods: {
        async addPesajeFinal() {
            try {
                this.ordenes = await OrdenService.addPesajeFinal(this.pesajeFinal,this.token)
                this.requestCompleteOrdenesPesajeFinal = true;
            } catch (error) {
                console.error(error);
            }
        }
    },

}

</script>

<style>

</style>
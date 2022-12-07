<template>
    <div class="container" v-if="!requestCompleteOrdenClosed">
        <table>
            <tr>
                <td>Token Generated:</td>
                <td><input id="input_token" type="text" v-model="token" /></td>
            </tr>
            <tr>
                <td>ID Orden:</td>
                <td><input type="text" v-model="id" /></td>
            </tr>
            <tr>
                <td><button @click="closeOrden">Cerrar Orden</button></td>
            </tr>
        </table>
    </div>

    <div v-if="requestCompleteOrdenClosed">
        <div class="alert alert-success" role="alert">
            <i class="fas fa-check-circle"></i> La petición se completó exitosamente
        </div>
    </div>
    <div v-else>
      <div class="alert alert-danger" role="alert">
        <i class="fas fa-times-circle"></i> Status de la Request({{ errorStatus }})
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
            requestCompleteOrdenClosed: false,
            id: '',
        }
    },
    methods: {
        async closeOrden() {
            try {
                this.ordenes = await OrdenService.closeOrden(this.id, this.token)
                this.requestCompleteOrdenClosed = true;
            } catch (error) {
                console.error(error);
            }
        }
    },

}

</script>

<style>

</style>
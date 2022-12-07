<template>
    <div class="container" v-if="!requestCompleteOrdenesConciliacion">
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
                <td><button @click="conciliacionOrden">Conciliacion de Orden</button></td>
            </tr>
        </table>
    </div>

    <div class="container" v-if="requestCompleteOrdenesConciliacion">
        <h1 class="text-center"> Lista de Ordenes</h1>

        <table class="table table-striped table-bordered">
            <thead>
                <th>Peso Inicial</th>
                <th>Peso Final</th>
                <th>Producto Cargado</th>
                <th>Neto por Balanza</th>
                <th>Diferencia entre Balanza/Caudalimetro</th>
                <th>Promedio Temperatura</th>
                <th>Promedio Densidad</th>
                <th>Promedio Caudal</th>
            </thead>
            <tbody>
                <tr>
                    <td> {{ pesoInicial }} </td>
                    <td> {{ pesoFinal }} </td>
                    <td> {{ productoCargado }} </td>
                    <td> {{ netoPorBalanza }} </td>
                    <td> {{ diferenciaEntreBalanzaCaudalímetro }} </td>
                    <td> {{ promedioTemperatura }} </td>
                    <td> {{ promedioDensidad }} </td>
                    <td> {{ promedioCaudal }} </td>
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
            requestCompleteOrdenesConciliacion: false,
            id: '',

        }
    },
    methods: {
        async conciliacionOrden() {
            try {
                const result = await OrdenService.conciliacionOrden(this.id, this.token)
                this.pesoInicial = result.pesoInicial;
                this.pesoFinal = result.pesoFinal;
                this.productoCargado = result.productoCargado;
                this.netoPorBalanza = result.netoPorBalanza;
                this.diferenciaEntreBalanzaCaudalímetro = result.diferenciaEntreBalanzaCaudalímetro;
                this.promedioTemperatura = result.promedioTemperatura;
                this.promedioDensidad = result.promedioDensidad;
                this.promedioCaudal = result.promedioCaudal;
                this.requestCompleteOrdenesConciliacion = true;
            } catch (error) {
                console.error(error);
            }
        }
    },

}

</script>

<style>

</style>
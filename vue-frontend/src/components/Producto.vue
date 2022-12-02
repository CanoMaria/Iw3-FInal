<template>
    <button id="id_button_productos" @click="getProductos" v-if="productos.length<=0">
      Listar productos
    </button>
    <div class="container" v-if="productos.length>0">
        <h1 class="text-center"> Lista de Productos</h1>
        <table class="table table-striped">
            <thead>
                <th>Producto ID</th>
                <th>Producto Nombre</th>
                <th>Producto Descripcion</th>
            </thead>
            <tbody>
                <tr v-for = "producto in productos" v-bind:key = "producto.id">
                <td> {{ producto.id }} </td>
                <td> {{ producto.nombre }} </td>
                <td> {{ producto.descripcion }} </td>
                </tr>
            </tbody>
        </table>

    </div>
</template>

<script>
import ProductoService from '@/services/ProductoService';

    export default{
        name: '',
        data(){
            return {
                productos : []
            }
        },
        methods:{
            getProductos(){
                ProductoService.getProductos().then((response) => {
                    this.productos = response.data
                })
            }
        },
        // created(){
        //     this.getProductos()
        // }

    }

</script>

<style>

</style>
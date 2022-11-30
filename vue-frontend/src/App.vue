<template>
  <div id="app">
    <Camion />
    <Producto />
    <Chofer />
    <Orden />
  </div>
</template>

<script>
import Camion from './components/Camion.vue'
import Producto from './components/Producto.vue'
import Chofer from './components/Chofer.vue'
import Orden from './components/Orden.vue'



export default {
  name: 'App',
  components: {
    Camion,
    Producto,
    Chofer,
    Orden
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>

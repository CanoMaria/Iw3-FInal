<template>
  <form @submit.prevent="login" class="container" v-if="!requestComplete">
    <table class="mx-auto">
      <tr>
        <td>Nombre de usuario:</td>
        <td><input type="text" v-model="username" /></td>
      </tr>


      <tr>
        <td>Contraseña:</td>
        <td><input type="password" v-model="password" /></td>
      </tr>

      <tr>
        <td><button @click="getTokens">Generar Token</button></td>
      </tr>
    </table>
  </form>

  <p v-if="error" class="error-message">{{ error }}</p>
  <!-- Añadir un <label> para mostrar el token -->

  <div class="alert alert-info" v-if="requestComplete" role="alert">
    Este token debe usarse en cada request deseada.
  </div>
  <label id="label_token" v-if="requestComplete" class="token-label">Token: {{ token }}</label>
</template>


<script>
import LoginService from '@/services/LoginService';


export default {
  name: 'LoginForm',
  data() {
    return {
      username: '',
      password: '',
      token: "",
      requestComplete: false,
      error: null
    };
  },
  methods: {
    async getTokens() {
      try {
        if (!this.username || !this.password) {
          this.error = 'Debes ingresar un nombre de usuario y una contraseña';
          return;
        }

        this.token = await LoginService.getTokens(this.username, this.password);

        this.requestComplete = true;

        return this.token
      } catch (error) {
        this.error = error;

        console.error(error);
      }
    }
  }
};
</script>
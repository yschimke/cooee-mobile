import React, { Component } from 'react';
import { AppRegistry, Text, View } from 'react-native';

export default class Todos extends Component {
  state = {
    loading: true,
    todos: []
  };

  async componentDidMount() {
    try {
      const response = await this.fetchTodos();
      console.log(response);
      this.setState({ todos: response.todos, loading: false });
    } catch (error) {
      console.error(error);
    }
  }

  async fetchTodos() {
    let response = await fetch(
      'https://api.coo.ee/api/v0/todo',
      {
        headers: {
          authorization: "Bearer eyJhbGciOiJIUzI1NiIsImV4cCI6MTU2MjUyMDg5N30.eyJlbWFpbCI6Inl1cmlAY29vLmVlIiwibmFtZSI6Ill1cmkgU2NoaW1rZSJ9.N3x0Ca5oOJtclFV2uEgX3VcGLCpVfjy06f6iVH7TSvE"
        }
      }
    );
    let responseJson = await response.json();
    return responseJson;
  }

  render() {
    if (this.state.loading) {
      return <Text>Loading</Text>;
    }

    return (
      <Text>Loaded {this.state.todos.length}</Text>
    );
  }
}
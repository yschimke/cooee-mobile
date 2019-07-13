import React, { Component } from 'react';
import { AppRegistry, Text, View, FlatList } from 'react-native';

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

  renderItem(item) {
    let message = null;

    if (item.message != null) {
      message = <Text>{item.message}</Text>;
    }

    return <View>
      <Text>{item.line}</Text>
      {message}
    </View>;
  }

  render() {
    if (this.state.loading) {
      return <Text>Loading</Text>;
    }

    return (
      <View>
        <Text>Todos {this.state.todos.length}</Text>
        <FlatList style={{ flex: 1 }}
          ref="todoList"
          data={this.state.todos}
          renderItem={({ item }) => this.renderItem(item)}
          keyExtractor={({ line }, index) => line}
        />
      </View>
    );
  }
}
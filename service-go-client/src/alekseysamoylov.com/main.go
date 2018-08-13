package main

import (
	"fmt"
	"./configuration"
	"./client"
)

func main() {
	fmt.Println("Go api client started")

	serverPort, serverRegion := configuration.GetConfiguration()

	fmt.Println(fmt.Sprintf("Server port %d", serverPort))
	fmt.Println("Server region " + serverRegion)
	client.StartApi(serverPort, serverRegion)
}

var instanceId string

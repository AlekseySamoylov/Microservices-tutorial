package configuration

import (
	"net/http"
	"os"
	"./request"
	"fmt"
)

func GetConfiguration() (int, string) {
	var defaultConfigUrl = "http://localhost:8889/service-go-client/region1"
	programArguments := os.Args

	fmt.Print("Program args = ")
	fmt.Println(programArguments)
	if programArguments != nil && len(programArguments) > 1 && programArguments[1] != "" {
		configUrl := programArguments[1]
		fmt.Print("Config Server URL = ")
		fmt.Println(configUrl)
		req, err := http.NewRequest("GET", configUrl, nil)
		return request.ConfigRequest(req, err)
	} else {
		req, err := http.NewRequest("GET", defaultConfigUrl, nil)
		return request.ConfigRequest(req, err)
	}
}

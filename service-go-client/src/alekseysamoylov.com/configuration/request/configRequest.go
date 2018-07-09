package request

import (
	"net/http"
	"log"
	"os"
	"fmt"
	"io/ioutil"
	"encoding/json"
	"time"
)

func ConfigRequest(req *http.Request, err error) (int, string) {
	if err != nil {
		log.Print(err)
		os.Exit(1)
	}

	goClient := http.Client{
		Timeout: time.Second * 2, // Maximum of 2 secs
	}

	req.Header.Set("User-Agent", "go-microservice-tutorial")

	res, getErr := goClient.Do(req)
	if getErr != nil {
		log.Fatal(getErr)
	}

	body, readErr := ioutil.ReadAll(res.Body)
	if readErr != nil {
		log.Fatal(readErr)
	}

	var clientConf clientConfiguration
	jsonErr := json.Unmarshal(body, &clientConf)
	if jsonErr != nil {
		log.Fatal(jsonErr)
	}

	fmt.Println(fmt.Sprintf("Go client name %s", clientConf.Name))

	return clientConf.PropertySources[0].Source.Port, clientConf.PropertySources[0].Source.Region
}

type clientConfiguration struct {
	Name string
	PropertySources []struct {
		Name string
		Source struct {
			Port      int
			Region    string
			Discovery string
		}
	}
}

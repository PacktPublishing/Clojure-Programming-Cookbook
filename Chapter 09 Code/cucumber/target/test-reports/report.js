$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("addition.feature");
formatter.feature({
  "line": 1,
  "name": "Addition",
  "description": "Building up, we want to repeat the same operation\nmany times",
  "id": "addition",
  "keyword": "Feature"
});
formatter.before({
  "duration": 55057,
  "status": "passed"
});
formatter.scenario({
  "line": 5,
  "name": "Add two numbers",
  "description": "",
  "id": "addition;add-two-numbers",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 6,
  "name": "I have entered 50 into the calculator",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "I have entered 70 into the calculator",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "I press add",
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "the result should be 120 on the screen",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "50",
      "offset": 15
    }
  ],
  "location": "addition_steps.clj:7"
});
formatter.result({
  "duration": 351730040,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "70",
      "offset": 15
    }
  ],
  "location": "addition_steps.clj:7"
});
formatter.result({
  "duration": 320290,
  "status": "passed"
});
formatter.match({
  "location": "addition_steps.clj:10"
});
formatter.result({
  "duration": 547019,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "120",
      "offset": 21
    }
  ],
  "location": "addition_steps.clj:13"
});
formatter.result({
  "duration": 282280,
  "status": "passed"
});
formatter.uri("conditional_order.feature");
formatter.feature({
  "line": 1,
  "name": "Conditional Order",
  "description": "In order to guard my positions\nAs a trader\nI want to send a trade order with conditional stop loss and take profit orders.",
  "id": "conditional-order",
  "keyword": "Feature"
});
formatter.before({
  "duration": 2974,
  "status": "passed"
});
formatter.scenario({
  "line": 6,
  "name": "Market Order with Take Profit and Stop Loss guards",
  "description": "",
  "id": "conditional-order;market-order-with-take-profit-and-stop-loss-guards",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 7,
  "name": "that my position in EURUSD is 0 at 1.34700",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "the market for EURUSD is at [1.34662;1.34714]",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "I have no open orders in EURUSD",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "I submit an order to BUY 1000000 EURUSD at MKT with TARGET 1.3800 and STOP 1.3200",
  "keyword": "When "
});
formatter.step({
  "line": 11,
  "name": "a trade should be made at 1.34714",
  "keyword": "Then "
});
formatter.step({
  "line": 12,
  "name": "my position should show LONG 1000000 EURUSD at 1.34714",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "EURUSD",
      "offset": 20
    },
    {
      "val": "0",
      "offset": 30
    },
    {
      "val": "1.34700",
      "offset": 35
    }
  ],
  "location": "open_position_steps.clj:41"
});
formatter.result({
  "duration": 160163,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "EURUSD",
      "offset": 15
    },
    {
      "val": "1.34662",
      "offset": 29
    },
    {
      "val": "1.34714",
      "offset": 37
    }
  ],
  "location": "open_position_steps.clj:55"
});
formatter.result({
  "duration": 260278,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "EURUSD",
      "offset": 25
    }
  ],
  "location": "open_position_steps.clj:114"
});
formatter.result({
  "duration": 2981789,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1000000",
      "offset": 25
    },
    {
      "val": "EURUSD",
      "offset": 33
    },
    {
      "val": "1.3800",
      "offset": 59
    },
    {
      "val": "1.3200",
      "offset": 75
    }
  ],
  "location": "open_position_steps.clj:118"
});
formatter.result({
  "duration": 8759492,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1.34714",
      "offset": 26
    }
  ],
  "location": "open_position_steps.clj:84"
});
formatter.result({
  "duration": 162006,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1000000",
      "offset": 29
    },
    {
      "val": "EURUSD",
      "offset": 37
    },
    {
      "val": "1.34714",
      "offset": 47
    }
  ],
  "location": "open_position_steps.clj:96"
});
formatter.result({
  "duration": 189471,
  "status": "passed"
});
formatter.uri("cuking.feature");
formatter.feature({
  "line": 1,
  "name": "Cuking",
  "description": "Working with tables",
  "id": "cuking",
  "keyword": "Feature"
});
formatter.before({
  "duration": 1823,
  "status": "passed"
});
formatter.scenario({
  "line": 4,
  "name": "Reading a table",
  "description": "",
  "id": "cuking;reading-a-table",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "the following cukes:",
  "rows": [
    {
      "cells": [
        "Niko",
        "10"
      ],
      "line": 6
    },
    {
      "cells": [
        "Taka",
        "20"
      ],
      "line": 7
    },
    {
      "cells": [
        "Kurata",
        "100"
      ],
      "line": 8
    },
    {
      "cells": [
        "Date",
        "120"
      ],
      "line": 9
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 10,
  "name": "the total should be 250.",
  "keyword": "Then "
});
formatter.match({
  "location": "cuking_steps.clj:20"
});
formatter.result({
  "duration": 12884450,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "250",
      "offset": 20
    }
  ],
  "location": "cuking_steps.clj:29"
});
formatter.result({
  "duration": 459586,
  "status": "passed"
});
formatter.before({
  "duration": 2146,
  "status": "passed"
});
formatter.scenario({
  "line": 12,
  "name": "Reading a table",
  "description": "",
  "id": "cuking;reading-a-table",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 13,
  "name": "the following cukes:",
  "rows": [
    {
      "cells": [
        "Niko",
        "10"
      ],
      "line": 14
    },
    {
      "cells": [
        "Taka",
        "20"
      ],
      "line": 15
    },
    {
      "cells": [
        "Kurata",
        "100"
      ],
      "line": 16
    },
    {
      "cells": [
        "Dave1",
        "100"
      ],
      "line": 17
    },
    {
      "cells": [
        "Dave2",
        "100"
      ],
      "line": 18
    },
    {
      "cells": [
        "Dave3",
        "100"
      ],
      "line": 19
    },
    {
      "cells": [
        "Dave4",
        "100"
      ],
      "line": 20
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 21,
  "name": "the total should be 530.",
  "keyword": "Then "
});
formatter.match({
  "location": "cuking_steps.clj:20"
});
formatter.result({
  "duration": 835340,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "530",
      "offset": 20
    }
  ],
  "location": "cuking_steps.clj:29"
});
formatter.result({
  "duration": 1186269,
  "status": "passed"
});
formatter.uri("cuking_csv.feature");
formatter.feature({
  "line": 1,
  "name": "Cuking with csv",
  "description": "I want to see how to cuke around with csv",
  "id": "cuking-with-csv",
  "keyword": "Feature"
});
formatter.before({
  "duration": 4934,
  "status": "passed"
});
formatter.scenario({
  "line": 4,
  "name": "Reading a table",
  "description": "",
  "id": "cuking-with-csv;reading-a-table",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "data from file \"set1.csv\":",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "the total should be 350.",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "set1.csv",
      "offset": 16
    }
  ],
  "location": "cuking_steps.clj:23"
});
formatter.result({
  "duration": 11634068,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "350",
      "offset": 20
    }
  ],
  "location": "cuking_steps.clj:29"
});
formatter.result({
  "duration": 94439,
  "status": "passed"
});
formatter.before({
  "duration": 2272,
  "status": "passed"
});
formatter.scenario({
  "line": 8,
  "name": "Same with property from command line",
  "description": "",
  "id": "cuking-with-csv;same-with-property-from-command-line",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 9,
  "name": "data from environment",
  "keyword": "Given "
});
formatter.step({
  "line": 10,
  "name": "the total should be 350.",
  "keyword": "Then "
});
formatter.match({
  "location": "cuking_steps.clj:26"
});
formatter.result({
  "duration": 1990270,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "350",
      "offset": 20
    }
  ],
  "location": "cuking_steps.clj:29"
});
formatter.result({
  "duration": 250187,
  "status": "passed"
});
formatter.uri("eep.feature");
formatter.feature({
  "line": 1,
  "name": "Working on Data Streaming",
  "description": "Not sure you need more information but here it comes",
  "id": "working-on-data-streaming",
  "keyword": "Feature"
});
formatter.before({
  "duration": 3062,
  "status": "passed"
});
formatter.scenario({
  "line": 4,
  "name": "This is one more scenario on data streaming",
  "description": "",
  "id": "working-on-data-streaming;this-is-one-more-scenario-on-data-streaming",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "the emitter is created",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "the aggregator is created",
  "keyword": "And "
});
formatter.step({
  "line": 7,
  "name": "some data is emitted to the aggregator",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "the state of the aggreagtor should be 45",
  "keyword": "Then "
});
formatter.match({
  "location": "eep_steps.clj:8"
});
formatter.result({
  "duration": 17802,
  "status": "passed"
});
formatter.match({
  "location": "eep_steps.clj:10"
});
formatter.result({
  "duration": 9865053,
  "status": "passed"
});
formatter.match({
  "location": "eep_steps.clj:18"
});
formatter.result({
  "duration": 1634320,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "45",
      "offset": 38
    }
  ],
  "location": "eep_steps.clj:24"
});
formatter.result({
  "duration": 217744,
  "status": "passed"
});
formatter.uri("excel.feature");
formatter.feature({
  "line": 1,
  "name": "My First Cucumber",
  "description": "Working with excel",
  "id": "my-first-cucumber",
  "keyword": "Feature"
});
formatter.before({
  "duration": 2106,
  "status": "passed"
});
formatter.scenario({
  "line": 4,
  "name": "Add two numbers",
  "description": "",
  "id": "my-first-cucumber;add-two-numbers",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "I use sheet \"List1\"",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "the result is validated",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "List1",
      "offset": 13
    }
  ],
  "location": "excel_defs.clj:23"
});
formatter.result({
  "duration": 885336467,
  "status": "passed"
});
formatter.match({
  "location": "excel_defs.clj:26"
});
formatter.result({
  "duration": 202305,
  "status": "passed"
});
formatter.uri("first.feature");
formatter.feature({
  "line": 1,
  "name": "My First Cucumber",
  "description": "Simple Example to add two numbers and assert the result",
  "id": "my-first-cucumber",
  "keyword": "Feature"
});
formatter.before({
  "duration": 2643,
  "status": "passed"
});
formatter.scenario({
  "line": 4,
  "name": "Add two numbers",
  "description": "",
  "id": "my-first-cucumber;add-two-numbers",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "I add 50 and 100",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "the result is 150",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "50",
      "offset": 6
    },
    {
      "val": "100",
      "offset": 13
    }
  ],
  "location": "first_steps.clj:6"
});
formatter.result({
  "duration": 363848,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "150",
      "offset": 14
    }
  ],
  "location": "first_steps.clj:9"
});
formatter.result({
  "duration": 189897,
  "status": "passed"
});
formatter.uri("flambo.feature");
formatter.feature({
  "line": 1,
  "name": "Flambing",
  "description": "This is a great way to put some notes about Flambing",
  "id": "flambing",
  "keyword": "Feature"
});
formatter.before({
  "duration": 2225,
  "status": "passed"
});
formatter.scenario({
  "line": 4,
  "name": "Word analysis in from a text file",
  "description": "",
  "id": "flambing;word-analysis-in-from-a-text-file",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "the following text file: \"fixtures/sherlock.txt\"",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "we count the number of lines using shell",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "we wanna check the number of lines is the same",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "fixtures/sherlock.txt",
      "offset": 26
    }
  ],
  "location": "flambo_steps.clj:10"
});
formatter.result({
  "duration": 158890,
  "status": "passed"
});
formatter.match({
  "location": "flambo_steps.clj:14"
});
formatter.result({
  "duration": 31724379,
  "status": "passed"
});
formatter.match({
  "location": "flambo_steps.clj:23"
});
formatter.result({
  "duration": 2009126209,
  "status": "passed"
});
formatter.uri("freactive.feature");
formatter.feature({
  "line": 1,
  "name": "My First Cucumber",
  "description": "Meeting people and counting ...",
  "id": "my-first-cucumber",
  "keyword": "Feature"
});
formatter.before({
  "duration": 4830,
  "status": "passed"
});
formatter.scenario({
  "line": 4,
  "name": "Meeting people",
  "description": "",
  "id": "my-first-cucumber;meeting-people",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "It is a new day",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "I met Niko",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "I met Erik",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "I met Dave",
  "keyword": "Given "
});
formatter.step({
  "line": 9,
  "name": "I have met 3 people and the last person was Dave",
  "keyword": "Then "
});
formatter.match({
  "location": "freactive_steps.clj:4"
});
formatter.result({
  "duration": 77853388,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Niko",
      "offset": 6
    }
  ],
  "location": "freactive_steps.clj:9"
});
formatter.result({
  "duration": 1389884,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Erik",
      "offset": 6
    }
  ],
  "location": "freactive_steps.clj:9"
});
formatter.result({
  "duration": 233577,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Dave",
      "offset": 6
    }
  ],
  "location": "freactive_steps.clj:9"
});
formatter.result({
  "duration": 93972,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "3",
      "offset": 11
    },
    {
      "val": "Dave",
      "offset": 44
    }
  ],
  "location": "freactive_steps.clj:16"
});
formatter.result({
  "duration": 480084,
  "status": "passed"
});
formatter.before({
  "duration": 2304,
  "status": "passed"
});
formatter.scenario({
  "line": 11,
  "name": "Meeting people again",
  "description": "",
  "id": "my-first-cucumber;meeting-people-again",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 12,
  "name": "It is a new day",
  "keyword": "Given "
});
formatter.step({
  "line": 13,
  "name": "I meet those people:",
  "rows": [
    {
      "cells": [
        "Niko"
      ],
      "line": 14
    },
    {
      "cells": [
        "Dave"
      ],
      "line": 15
    },
    {
      "cells": [
        "Erik"
      ],
      "line": 16
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 17,
  "name": "I have met 3 people and the last person was Erik",
  "keyword": "Then "
});
formatter.match({
  "location": "freactive_steps.clj:4"
});
formatter.result({
  "duration": 41357,
  "status": "passed"
});
formatter.match({
  "location": "freactive_steps.clj:12"
});
formatter.result({
  "duration": 437273,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "3",
      "offset": 11
    },
    {
      "val": "Erik",
      "offset": 44
    }
  ],
  "location": "freactive_steps.clj:16"
});
formatter.result({
  "duration": 82134,
  "status": "passed"
});
formatter.uri("http.feature");
formatter.feature({
  "line": 1,
  "name": "My First Cucumber",
  "description": "HTTP and Mongo. Retrieve weather info from HTTP \nand compute average using MongoDB",
  "id": "my-first-cucumber",
  "keyword": "Feature"
});
formatter.before({
  "duration": 2313,
  "status": "passed"
});
formatter.scenario({
  "line": 5,
  "name": "Storing Data For Cities",
  "description": "",
  "id": "my-first-cucumber;storing-data-for-cities",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 6,
  "name": "I load weather data for the following cities:",
  "rows": [
    {
      "cells": [
        "Tokyo"
      ],
      "line": 7
    },
    {
      "cells": [
        "Osaka"
      ],
      "line": 8
    },
    {
      "cells": [
        "Kobe"
      ],
      "line": 9
    },
    {
      "cells": [
        "Fukuoka"
      ],
      "line": 10
    },
    {
      "cells": [
        "Sapporo"
      ],
      "line": 11
    },
    {
      "cells": [
        "Nagano"
      ],
      "line": 12
    },
    {
      "cells": [
        "Nara"
      ],
      "line": 13
    },
    {
      "cells": [
        "Yokohama"
      ],
      "line": 14
    }
  ],
  "keyword": "When "
});
formatter.step({
  "line": 15,
  "name": "I expect the average to be lower than 30",
  "keyword": "Then "
});
formatter.match({
  "location": "http_defs.clj:40"
});
formatter.result({
  "duration": 4938889357,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "30",
      "offset": 38
    }
  ],
  "location": "http_defs.clj:45"
});
formatter.result({
  "duration": 38289011,
  "status": "passed"
});
formatter.uri("open_position.feature");
formatter.feature({
  "line": 1,
  "name": "Open Position",
  "description": "In order to open a position\nAs a trader\nI want to send a trade order",
  "id": "open-position",
  "keyword": "Feature"
});
formatter.before({
  "duration": 4810,
  "status": "passed"
});
formatter.scenario({
  "line": 6,
  "name": "Market Order BUY",
  "description": "",
  "id": "open-position;market-order-buy",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 7,
  "name": "that my position in EURUSD is 0 at 1.34700",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "the market for EURUSD is at [1.34662;1.34714]",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "I submit an order to BUY 1000000 EURUSD at MKT",
  "keyword": "When "
});
formatter.step({
  "line": 10,
  "name": "a trade should be made at 1.34714",
  "keyword": "Then "
});
formatter.step({
  "line": 11,
  "name": "my position should show LONG 1000000 EURUSD at 1.34714",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "EURUSD",
      "offset": 20
    },
    {
      "val": "0",
      "offset": 30
    },
    {
      "val": "1.34700",
      "offset": 35
    }
  ],
  "location": "open_position_steps.clj:41"
});
formatter.result({
  "duration": 99530,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "EURUSD",
      "offset": 15
    },
    {
      "val": "1.34662",
      "offset": 29
    },
    {
      "val": "1.34714",
      "offset": 37
    }
  ],
  "location": "open_position_steps.clj:55"
});
formatter.result({
  "duration": 125608,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1000000",
      "offset": 25
    },
    {
      "val": "EURUSD",
      "offset": 33
    }
  ],
  "location": "open_position_steps.clj:66"
});
formatter.result({
  "duration": 130617,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1.34714",
      "offset": 26
    }
  ],
  "location": "open_position_steps.clj:84"
});
formatter.result({
  "duration": 81735,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1000000",
      "offset": 29
    },
    {
      "val": "EURUSD",
      "offset": 37
    },
    {
      "val": "1.34714",
      "offset": 47
    }
  ],
  "location": "open_position_steps.clj:96"
});
formatter.result({
  "duration": 107563,
  "status": "passed"
});
formatter.scenarioOutline({
  "line": 13,
  "name": "Market Order SELL",
  "description": "",
  "id": "open-position;market-order-sell",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 14,
  "name": "that my position in EURUSD is 0 at 1.34700",
  "keyword": "Given "
});
formatter.step({
  "line": 15,
  "name": "the market for EURUSD is at [\u003cbid\u003e;\u003cask\u003e]",
  "keyword": "And "
});
formatter.step({
  "line": 16,
  "name": "I submit an order to SELL \u003cquantity\u003e EURUSD at MKT",
  "keyword": "When "
});
formatter.step({
  "line": 17,
  "name": "a trade should be made at \u003cbid\u003e",
  "keyword": "Then "
});
formatter.step({
  "line": 18,
  "name": "my position should show SHORT \u003cquantity\u003e EURUSD at \u003cbid\u003e",
  "keyword": "And "
});
formatter.examples({
  "line": 20,
  "name": "",
  "description": "",
  "id": "open-position;market-order-sell;",
  "rows": [
    {
      "cells": [
        "bid",
        "ask",
        "quantity"
      ],
      "line": 21,
      "id": "open-position;market-order-sell;;1"
    },
    {
      "cells": [
        "1.34662",
        "1.34714",
        "1000000"
      ],
      "line": 22,
      "id": "open-position;market-order-sell;;2"
    },
    {
      "cells": [
        "1.40000",
        "1.40050",
        "1000000"
      ],
      "line": 23,
      "id": "open-position;market-order-sell;;3"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 3846,
  "status": "passed"
});
formatter.scenario({
  "line": 22,
  "name": "Market Order SELL",
  "description": "",
  "id": "open-position;market-order-sell;;2",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 14,
  "name": "that my position in EURUSD is 0 at 1.34700",
  "keyword": "Given "
});
formatter.step({
  "line": 15,
  "name": "the market for EURUSD is at [1.34662;1.34714]",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "And "
});
formatter.step({
  "line": 16,
  "name": "I submit an order to SELL 1000000 EURUSD at MKT",
  "matchedColumns": [
    2
  ],
  "keyword": "When "
});
formatter.step({
  "line": 17,
  "name": "a trade should be made at 1.34662",
  "matchedColumns": [
    0
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 18,
  "name": "my position should show SHORT 1000000 EURUSD at 1.34662",
  "matchedColumns": [
    0,
    2
  ],
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "EURUSD",
      "offset": 20
    },
    {
      "val": "0",
      "offset": 30
    },
    {
      "val": "1.34700",
      "offset": 35
    }
  ],
  "location": "open_position_steps.clj:41"
});
formatter.result({
  "duration": 108928,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "EURUSD",
      "offset": 15
    },
    {
      "val": "1.34662",
      "offset": 29
    },
    {
      "val": "1.34714",
      "offset": 37
    }
  ],
  "location": "open_position_steps.clj:55"
});
formatter.result({
  "duration": 213624,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1000000",
      "offset": 26
    },
    {
      "val": "EURUSD",
      "offset": 34
    }
  ],
  "location": "open_position_steps.clj:77"
});
formatter.result({
  "duration": 178125,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1.34662",
      "offset": 26
    }
  ],
  "location": "open_position_steps.clj:84"
});
formatter.result({
  "duration": 43932,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1000000",
      "offset": 30
    },
    {
      "val": "EURUSD",
      "offset": 38
    },
    {
      "val": "1.34662",
      "offset": 48
    }
  ],
  "location": "open_position_steps.clj:108"
});
formatter.result({
  "duration": 92931,
  "status": "passed"
});
formatter.before({
  "duration": 2177,
  "status": "passed"
});
formatter.scenario({
  "line": 23,
  "name": "Market Order SELL",
  "description": "",
  "id": "open-position;market-order-sell;;3",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 14,
  "name": "that my position in EURUSD is 0 at 1.34700",
  "keyword": "Given "
});
formatter.step({
  "line": 15,
  "name": "the market for EURUSD is at [1.40000;1.40050]",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "And "
});
formatter.step({
  "line": 16,
  "name": "I submit an order to SELL 1000000 EURUSD at MKT",
  "matchedColumns": [
    2
  ],
  "keyword": "When "
});
formatter.step({
  "line": 17,
  "name": "a trade should be made at 1.40000",
  "matchedColumns": [
    0
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 18,
  "name": "my position should show SHORT 1000000 EURUSD at 1.40000",
  "matchedColumns": [
    0,
    2
  ],
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "EURUSD",
      "offset": 20
    },
    {
      "val": "0",
      "offset": 30
    },
    {
      "val": "1.34700",
      "offset": 35
    }
  ],
  "location": "open_position_steps.clj:41"
});
formatter.result({
  "duration": 125074,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "EURUSD",
      "offset": 15
    },
    {
      "val": "1.40000",
      "offset": 29
    },
    {
      "val": "1.40050",
      "offset": 37
    }
  ],
  "location": "open_position_steps.clj:55"
});
formatter.result({
  "duration": 105821,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1000000",
      "offset": 26
    },
    {
      "val": "EURUSD",
      "offset": 34
    }
  ],
  "location": "open_position_steps.clj:77"
});
formatter.result({
  "duration": 66048,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1.40000",
      "offset": 26
    }
  ],
  "location": "open_position_steps.clj:84"
});
formatter.result({
  "duration": 45258,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1000000",
      "offset": 30
    },
    {
      "val": "EURUSD",
      "offset": 38
    },
    {
      "val": "1.40000",
      "offset": 48
    }
  ],
  "location": "open_position_steps.clj:108"
});
formatter.result({
  "duration": 165723,
  "status": "passed"
});
formatter.uri("opencv.feature");
formatter.feature({
  "line": 1,
  "name": "OpenCV Face Recognition",
  "description": "Playing around with face recognition and cucumber",
  "id": "opencv-face-recognition",
  "keyword": "Feature"
});
formatter.before({
  "duration": 5292,
  "status": "passed"
});
formatter.scenario({
  "line": 4,
  "name": "Looking for faces in an image",
  "description": "",
  "id": "opencv-face-recognition;looking-for-faces-in-an-image",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "I load the image \"fixtures/lena.png\"",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "setup the classifier for frontal face recognition",
  "keyword": "And "
});
formatter.step({
  "line": 7,
  "name": "I can find 1 face in the loaded image",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "fixtures/lena.png",
      "offset": 18
    }
  ],
  "location": "opencv_steps.clj:12"
});
formatter.result({
  "duration": 13976486,
  "status": "passed"
});
formatter.match({
  "location": "opencv_steps.clj:15"
});
formatter.result({
  "duration": 5630027,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 11
    }
  ],
  "location": "opencv_steps.clj:18"
});
formatter.result({
  "duration": 85557633,
  "status": "passed"
});
formatter.before({
  "duration": 2584,
  "status": "passed"
});
formatter.scenario({
  "line": 9,
  "name": "Looking for faces in an image that is not from the opencv example",
  "description": "",
  "id": "opencv-face-recognition;looking-for-faces-in-an-image-that-is-not-from-the-opencv-example",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 10,
  "name": "I load the image \"fixtures/friendsbig.jpg\"",
  "keyword": "When "
});
formatter.step({
  "line": 11,
  "name": "setup the classifier for frontal face recognition",
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "I can find 6 face in the loaded image",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "fixtures/friendsbig.jpg",
      "offset": 18
    }
  ],
  "location": "opencv_steps.clj:12"
});
formatter.result({
  "duration": 72563047,
  "status": "passed"
});
formatter.match({
  "location": "opencv_steps.clj:15"
});
formatter.result({
  "duration": 1665790,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "6",
      "offset": 11
    }
  ],
  "location": "opencv_steps.clj:18"
});
formatter.result({
  "duration": 673784816,
  "status": "passed"
});
formatter.uri("virtualbox.feature");
formatter.feature({
  "line": 1,
  "name": "VirtualBox Cucumber",
  "description": "Example on using VirtualBox with Cucumber",
  "id": "virtualbox-cucumber",
  "keyword": "Feature"
});
formatter.before({
  "duration": 3171,
  "status": "passed"
});
formatter.scenario({
  "line": 4,
  "name": "Simple VirtualBox Interaction",
  "description": "",
  "id": "virtualbox-cucumber;simple-virtualbox-interaction",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "I start the VM \"debian\"",
  "keyword": "When "
});
formatter.step({
  "comments": [
    {
      "line": 6,
      "value": "# And Wait 10 seconds"
    }
  ],
  "line": 7,
  "name": "IP \"172.16.2.204\" has port \"22\" opened",
  "keyword": "Then "
});
formatter.step({
  "line": 8,
  "name": "I can stop the VM \"debian\"",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "debian",
      "offset": 16
    }
  ],
  "location": "virtualbox_defs.clj:17"
});
formatter.result({
  "duration": 534402976,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "172.16.2.204",
      "offset": 4
    },
    {
      "val": "22",
      "offset": 28
    }
  ],
  "location": "virtualbox_defs.clj:23"
});
formatter.result({
  "duration": 67455270862,
  "error_message": "java.lang.AssertionError: Assert failed: (\u003d 0 (:exit cmd))\n\tat cucumber.runtime.clj$eval12194$fn__12195.invoke(virtualbox_defs.clj:25)\n\tat clojure.lang.AFn.applyToHelper(AFn.java:156)\n\tat clojure.lang.AFn.applyTo(AFn.java:144)\n\tat clojure.core$apply.invokeStatic(core.clj:651)\n\tat clojure.core$apply.invoke(core.clj:646)\n\tat cucumber.runtime.clj$add_step_definition$reify__2252.execute(clj.clj:83)\n\tat cucumber.runtime.StepDefinitionMatch.runStep(StepDefinitionMatch.java:37)\n\tat cucumber.runtime.Runtime.runStep(Runtime.java:298)\n\tat cucumber.runtime.model.StepContainer.runStep(StepContainer.java:44)\n\tat cucumber.runtime.model.StepContainer.runSteps(StepContainer.java:39)\n\tat cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:48)\n\tat cucumber.runtime.model.CucumberFeature.run(CucumberFeature.java:163)\n\tat cucumber.runtime.Runtime.run(Runtime.java:120)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.lang.reflect.Method.invoke(Method.java:497)\n\tat clojure.lang.Reflector.invokeMatchingMethod(Reflector.java:93)\n\tat clojure.lang.Reflector.invokeNoArgInstanceMember(Reflector.java:313)\n\tat leiningen.cucumber.util$run_cucumber_BANG_$fn__19.invoke(util.clj:31)\n\tat leiningen.cucumber.util$run_cucumber_BANG_.invokeStatic(util.clj:30)\n\tat leiningen.cucumber.util$run_cucumber_BANG_.invoke(util.clj:23)\n\tat user$eval2201.invokeStatic(form-init3636687208463000433.clj:1)\n\tat user$eval2201.invoke(form-init3636687208463000433.clj:1)\n\tat clojure.lang.Compiler.eval(Compiler.java:6942)\n\tat clojure.lang.Compiler.eval(Compiler.java:6932)\n\tat clojure.lang.Compiler.eval(Compiler.java:6932)\n\tat clojure.lang.Compiler.load(Compiler.java:7394)\n\tat clojure.lang.Compiler.loadFile(Compiler.java:7332)\n\tat clojure.main$load_script.invokeStatic(main.clj:275)\n\tat clojure.main$init_opt.invokeStatic(main.clj:277)\n\tat clojure.main$init_opt.invoke(main.clj:277)\n\tat clojure.main$initialize.invokeStatic(main.clj:308)\n\tat clojure.main$null_opt.invokeStatic(main.clj:342)\n\tat clojure.main$null_opt.invoke(main.clj:339)\n\tat clojure.main$main.invokeStatic(main.clj:421)\n\tat clojure.main$main.doInvoke(main.clj:384)\n\tat clojure.lang.RestFn.invoke(RestFn.java:421)\n\tat clojure.lang.Var.invoke(Var.java:383)\n\tat clojure.lang.AFn.applyToHelper(AFn.java:156)\n\tat clojure.lang.Var.applyTo(Var.java:700)\n\tat clojure.main.main(main.java:37)\n\tat ✽.Then IP \"172.16.2.204\" has port \"22\" opened(virtualbox.feature:7)\n",
  "status": "failed"
});
formatter.match({
  "arguments": [
    {
      "val": "debian",
      "offset": 19
    }
  ],
  "location": "virtualbox_defs.clj:27"
});
formatter.result({
  "status": "skipped"
});
});
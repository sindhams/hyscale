/**
 * Copyright 2019 Pramati Prism, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.hyscale.servicespec.commons.model.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.hyscale.servicespec.commons.fields.HyscaleSpecFields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ImageDeserializer extends JsonDeserializer {

    private static final Logger logger = LoggerFactory.getLogger(ImageDeserializer.class);

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode specNode = jsonParser.readValueAsTree();
        BuildSpecImage buildSpecImage = new BuildSpecImage();
        if(specNode.has(HyscaleSpecFields.buildSpec)){
            ObjectMapper objectMapper = new ObjectMapper();
            buildSpecImage = objectMapper.readValue(specNode.toString(), BuildSpecImage.class);
        }
        return buildSpecImage;
    }
}

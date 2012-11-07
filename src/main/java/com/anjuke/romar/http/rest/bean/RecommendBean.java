/**
 * Copyright 2012 Anjuke Inc.
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
package com.anjuke.romar.http.rest.bean;

public class RecommendBean {
    private long _item;
    private float _value;

    public long getItem() {
        return _item;
    }

    public void setItem(long item) {
        _item = item;
    }

    public float getValue() {
        return _value;
    }

    public void setValue(float value) {
        _value = value;
    }

    @Override
    public String toString() {
        return "RecommendBean [_item=" + _item + ", _value=" + _value + "]";
    }

}
